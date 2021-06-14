package com.example.newmovieapp.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newmovieapp.R
import com.example.newmovieapp.adapter.FavoriteTvAdapter
import com.example.newmovieapp.adapter.MovieAdapter
import com.example.newmovieapp.databinding.FragmentMovieFavoriteBinding
import com.example.newmovieapp.model.Movie
import com.example.newmovieapp.static.Const
import com.example.newmovieapp.util.gone
import com.example.newmovieapp.util.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TvFavoriteFragment : Fragment(), MovieAdapter.OnItemClickListener {


    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var binding: FragmentMovieFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteTvs()

    }

    private fun getFavoriteTvs() {
        favoriteViewModel.getTvs().observe(viewLifecycleOwner, {
            favoriteViewModel.getTvs().observe(viewLifecycleOwner, {
                binding.progressBarMovieFav.gone()
                if (it.isNotEmpty()) {
                    binding.noDataView.gone()
                    val adapter = FavoriteTvAdapter()
                    adapter.submitList(it)
                    adapter.onItemClick = this
                    binding.rvListFavoriteMovie.layoutManager =
                        GridLayoutManager(requireContext(), 2)
                    binding.rvListFavoriteMovie.adapter = adapter
                    binding.rvListFavoriteMovie.visible()
                } else {
                    binding.noDataView.visible()
                }

            })
        })
    }

    override fun onItemClick(movie: Movie?, view: View) {
        val bundle = Bundle()
        bundle.putBoolean(Const.IS_MOVIE, false)
        bundle.putInt(Const.MOVIE_ID, movie?.id ?: 0)
        findNavController().navigate(R.id.action_favoriteFragment_to_detailMovieFragment, bundle)
    }
}
