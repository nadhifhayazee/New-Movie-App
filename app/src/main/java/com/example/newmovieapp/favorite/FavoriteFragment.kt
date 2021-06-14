package com.example.newmovieapp.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newmovieapp.adapter.TabAdapter
import com.example.newmovieapp.databinding.FragmentFavoriteBinding
import com.example.newmovieapp.util.resetStatusBarColor
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var tabAdapter: TabAdapter
    private val tabsTitle = listOf("Film", "Tv")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.resetStatusBarColor()
        tabAdapter = TabAdapter(this)
        tabAdapter.addFragment(MovieFavoriteFragment())
        tabAdapter.addFragment(TvFavoriteFragment())
        binding.viewPager.adapter = tabAdapter
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            tab.text = tabsTitle[position]
        }.attach()
    }


}