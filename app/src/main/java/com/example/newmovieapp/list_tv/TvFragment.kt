package com.example.newmovieapp.list_tv

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newmovieapp.R
import com.example.newmovieapp.activity.MainActivity
import com.example.newmovieapp.adapter.MovieAdapter
import com.example.newmovieapp.adapter.MovieCarouselAdapter
import com.example.newmovieapp.adapter.MovieType
import com.example.newmovieapp.databinding.FragmentMovieBinding
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.RequestStatus
import com.example.newmovieapp.static.Const
import com.example.newmovieapp.util.gone
import com.example.newmovieapp.util.visible
import com.google.android.material.snackbar.Snackbar
import java.util.*


class TvFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val tvViewModel by navGraphViewModels<TvViewModel>(R.id.main_navigation)

    private lateinit var carouselAdapter: MovieCarouselAdapter

    private val popularAdapter = MovieAdapter(MovieType.POPULAR)

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
        tvViewModel.requestPopularTv()
        tvViewModel.requestNowPlayingTv()
        setupView()
        initObserver()
    }

    private fun initObserver() {
        tvViewModel.popularTv.observe(viewLifecycleOwner, {
            when (it.requestStatus) {
                RequestStatus.LOADING -> {
                    binding.progressBar.visible()
                }
                RequestStatus.SUCCESS -> {
                    binding.progressBar.gone()
                    it.data?.results?.let { list -> popularAdapter.setData(list) }
                    popularAdapter.onItemClick = this
                    binding.rvListMovie.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvListMovie.adapter = popularAdapter
                    binding.rvListMovie.visible()
                }
                RequestStatus.ERROR -> {
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

        tvViewModel.nowPlayingTv.observe(viewLifecycleOwner, { state ->
            when (state.requestStatus) {
                RequestStatus.LOADING -> {
                    binding.movieCarousel.gone()
                }
                RequestStatus.SUCCESS -> {
                    state.data?.results?.let {
                        binding.movieCarousel.visible()
                        carouselAdapter = MovieCarouselAdapter()
                        carouselAdapter.setData(it)
                        binding.movieCarousel.adapter = carouselAdapter
                        carouselPosition = binding.movieCarousel.viewPager2.currentItem
                        setCarouselTimer()

                    }


                }
                RequestStatus.ERROR -> {
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
            if (carouselPosition == Const.CAROUSEL_SIZE) {
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

    private fun setupView() {
        binding.tvForYou.text = getString(R.string.tv_for_you)
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

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            if (mActivity == null) mActivity = activity as MainActivity
            mActivity?.showBottomNavigation()
        }
    }

    override fun onItemClick(movie: Movie?, view: View) {
        val bundle = Bundle()
        bundle.putBoolean(Const.IS_MOVIE, false)
        bundle.putInt(Const.MOVIE_ID, movie?.id ?: 0)
        findNavController().navigate(R.id.action_tvFragment_to_detailMovieFragment, bundle)
    }

}