package com.example.newmovieapp.list_movie

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newmovieapp.R
import com.example.newmovieapp.activity.MainActivity
import com.example.newmovieapp.adapter.MovieAdapter
import com.example.newmovieapp.adapter.MovieCarouselAdapter
import com.example.newmovieapp.databinding.FragmentMovieBinding
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.RequestStatus
import com.example.newmovieapp.static.Const.Companion.CAROUSEL_SIZE
import com.example.newmovieapp.static.Const.Companion.IS_MOVIE
import com.example.newmovieapp.static.Const.Companion.MOVIE_ID
import com.example.newmovieapp.util.gone
import com.example.newmovieapp.util.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var binding: FragmentMovieBinding
    private var carouselPosition = 1
    private var mActivity: MainActivity? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observePlayingMovies()
        observePopularMovies()
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            if (mActivity == null) mActivity = activity as MainActivity
            mActivity?.showBottomNavigation()
        }
    }


    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY <= 250) {
                    binding.toolbar.setBackgroundResource(
                        R.drawable.bg_gradient_toolbar
                    )
                    binding.toolbarTitle.gone()
                } else {
                    binding.toolbar.setBackgroundColor(
                        ContextCompat.getColor(requireContext(), R.color.blackBackground)
                    )
                    binding.toolbarTitle.visible()
                }
            }
        }
    }

    private fun observePopularMovies() {
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, {
            when (it.requestStatus) {
                RequestStatus.LOADING -> {
                    binding.progressBar.visible()
                    binding.rvListMovie.gone()

                }
                RequestStatus.SUCCESS -> {
                    binding.progressBar.gone()
                    it.data?.let { list ->
                        val popularAdapter = MovieAdapter(list)
                        popularAdapter.onItemClick = this
                        binding.rvListMovie.layoutManager = GridLayoutManager(requireContext(), 2)
                        binding.rvListMovie.adapter = popularAdapter
                        binding.rvListMovie.visible()
                    }

                }
                else -> {
                    binding.progressBar.gone()
                    it.message?.let { it1 ->
                        Snackbar.make(
                            binding.root,
                            it1, Snackbar.LENGTH_SHORT
                        )
                    }
                }
            }
        })
    }


    private fun observePlayingMovies() {
        movieViewModel.getNowPlayingMovies().observe(viewLifecycleOwner, { state ->
            when (state.requestStatus) {
                RequestStatus.LOADING -> {
                    binding.progressBar.visible()
                    binding.movieCarousel.gone()
                }


                RequestStatus.SUCCESS -> {
                    state.data?.let {
                        binding.movieCarousel.visible()
                        val carouselAdapter = MovieCarouselAdapter()
                        carouselAdapter.setData(it)
                        binding.movieCarousel.adapter = carouselAdapter
                        carouselPosition = binding.movieCarousel.viewPager2.currentItem
                        setCarouselTimer()

                    }

                }
                else -> {
                    state.message?.let { it1 ->
                        Snackbar.make(
                            binding.root,
                            it1, Snackbar.LENGTH_SHORT
                        )
                    }
                }
            }
        })
    }

    private fun setCarouselTimer() {
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            if (carouselPosition == CAROUSEL_SIZE) {
                carouselPosition = 0
            }
            binding.movieCarousel.viewPager2.setCurrentItem(carouselPosition++, true)

        }

        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3500, 3500)
    }

    override fun onItemClick(movie: Movie?, view: View) {
        val bundle = Bundle()
        bundle.putBoolean(IS_MOVIE, true)
        bundle.putInt(MOVIE_ID, movie?.id ?: 0)
        findNavController().navigate(R.id.action_movieFragment_to_detailMovieFragment, bundle)
    }
}