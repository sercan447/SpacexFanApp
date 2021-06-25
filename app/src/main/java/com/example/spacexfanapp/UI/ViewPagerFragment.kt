package com.example.spacexfanapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacexfanapp.R
import com.example.spacexfanapp.adapter.ViewPagerAdapter
import com.example.spacexfanapp.databinding.FragmentRocketsBinding
import com.example.spacexfanapp.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       // val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        _binding = FragmentViewPagerBinding.inflate(inflater,container,false)

        val fragmentList = arrayListOf<Fragment>(
            LauncherFragment(),
            RocketsFragment(),
            FavoritesFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )


        // TabLayout
        val tabLayout = binding.tabLayout
        // ViewPager2
        val viewPager = binding.viewPager

        tabLayout.addTab(binding.tabLayout.newTab().setText("Launcher"))
        tabLayout.addTab(binding.tabLayout.newTab().setText("Rockets"))
        tabLayout.addTab(binding.tabLayout.newTab().setText("Favorites"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        binding.viewPager.adapter = adapter



        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0->  tab.text = "Launches"
                1->  tab.text = "Rockets"
                2->  tab.text = "Favorites"
            }
        }.attach()


        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}