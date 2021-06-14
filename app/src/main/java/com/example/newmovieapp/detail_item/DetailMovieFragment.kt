package com.example.newmovieapp.detail_item

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newmovieapp.BuildConfig
import com.example.newmovieapp.R
import com.example.newmovieapp.activity.MainActivity
import com.example.newmovieapp.databinding.FragmentDetailMovieBinding
import com.example.newmovieapp.db.entity.MovieEntity
import com.example.newmovieapp.db.entity.TvEntity
import com.example.newmovieapp.favorite.FavoriteViewModel
import com.example.newmovieapp.favorite.helper.InsertResponse
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.network.RequestStatus
import com.example.newmovieapp.static.Const
import com.example.newmovieapp.util.FavoriteMapper
import com.example.newmovieapp.util.gone
import com.example.newmovieapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private var mActivity: MainActivity? = null
    private lateinit var binding: FragmentDetailMovieBinding
    private var isMovie = true
    private var movieId = 0
    private val detailViewModel by viewModels<DetailViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isMovie = it.getBoolean(Const.IS_MOVIE)
            movieId = it.getInt(Const.MOVIE_ID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initObserver()

    }

    private fun initObserver() {
        if (isMovie) {
            detailViewModel.getDetailMovie(movieId.toString()).observe(viewLifecycleOwner, {
                when (it.requestStatus) {
                    RequestStatus.LOADING -> {
                        binding.progressBar.visible()
                    }
                    RequestStatus.SUCCESS -> {
                        binding.progressBar.gone()
                        bindView(it.data)
                    }
                    RequestStatus.ERROR -> {
                        binding.progressBar.gone()

                    }
                }

            })

        } else {
            detailViewModel.getDetailTv(movieId.toString()).observe(viewLifecycleOwner, {
                when (it.requestStatus) {
                    RequestStatus.LOADING -> {
                        binding.progressBar.visible()

                    }
                    RequestStatus.SUCCESS -> {
                        binding.progressBar.gone()
                        bindView(it.data)
                    }
                    RequestStatus.ERROR -> {
                        binding.progressBar.gone()

                    }
                }
            })
        }


    }

    private fun bindView(data: Movie?) {
        with(binding) {
            val rating = if (data?.vote_average.toString()
                    .isNotEmpty()
            ) data?.vote_average.toString() else "-"
            val overview = if (data?.overview?.isNotEmpty() == true) data.overview else "-"
            tvTitleDetail.text = data?.title ?: data?.original_name
            tvGenres.text = data?.getGenres()
            tvRuntime.text = getString(
                R.string.movie_runtime,
                if (isMovie) data?.runtime.toString() else data?.episode_run_time?.getOrNull(0)
                    .toString()
            )
            tvRating.text = rating
            tvOverview.text = overview
            Glide.with(requireContext())
                .load(BuildConfig.BACKDROP_URL + data?.backdrop_path)
                .into(ivBackdropDetail)
            Glide.with(requireContext())
                .load(BuildConfig.IMAGE_URL + data?.poster_path)
                .into(ivPosterDetail)

            if (isMovie) {
                checkIsFavoriteMovie(data?.id ?: 0)
                ivFavorite.setOnClickListener {
                    val movieEntity = MovieEntity()
                    movieEntity.movie_id = data?.id ?: 0
                    movieEntity.data = FavoriteMapper.movieToJson(data)
                    insertMovie(movieEntity)
                }
            } else {
                checkIsFavoriteTv(data?.id ?: 0)
                ivFavorite.setOnClickListener {
                    val tvEntity = TvEntity()
                    tvEntity.tv_id = data?.id ?: 0
                    tvEntity.data = FavoriteMapper.movieToJson(data)
                    insertTv(tvEntity)
                }
            }
        }


    }

    private fun checkIsFavoriteTv(id: Int) {
        favoriteViewModel.checkIsFavoriteTv(id).observe(viewLifecycleOwner, {
            when (it.requestStatus) {
                RequestStatus.SUCCESS -> {
                    setViewIsFavorite(it.data == true)
                }
            }
        })
    }

    private fun checkIsFavoriteMovie(id: Int) {
        favoriteViewModel.checkIsFavoriteMovie(id).observe(viewLifecycleOwner, {
            when (it.requestStatus) {
                RequestStatus.SUCCESS -> {
                    setViewIsFavorite(it.data == true)
                }
            }
        })
    }

    private fun insertTv(tvEntity: TvEntity) {
        favoriteViewModel.insertTv(tvEntity).observe(viewLifecycleOwner, {
            when (it.requestStatus) {
                RequestStatus.SUCCESS -> {
                    setViewIsFavorite(it.data == InsertResponse.INSERTED)
                }
            }
        })
    }

    private fun insertMovie(movieEntity: MovieEntity) {
        favoriteViewModel.insertMovie(movieEntity).observe(viewLifecycleOwner, {
            when (it.requestStatus) {
                RequestStatus.SUCCESS -> {
                    setViewIsFavorite(it.data == InsertResponse.INSERTED)
                }
            }

        })
    }

    private fun setViewIsFavorite(isFavorite: Boolean) {
        if (isFavorite) binding.ivFavorite.setBackgroundResource(R.drawable.ic_favorite_filled)
        else binding.ivFavorite.setBackgroundResource(R.drawable.ic_favorite_border)
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY <= 250) {
                    binding.toolbar.setBackgroundResource(
                        R.drawable.bg_gradient_toolbar
                    )
                } else {
                    binding.toolbar.setBackgroundColor(
                        ContextCompat.getColor(requireContext(), R.color.blackBackground)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (activity is MainActivity) {
            if (mActivity == null) mActivity = activity as MainActivity
            mActivity?.hideBottomNavigation()
        }
    }
}