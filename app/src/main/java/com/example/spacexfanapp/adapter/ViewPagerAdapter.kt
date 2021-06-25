package com.example.spacexfanapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spacexfanapp.UI.FavoritesFragment
import com.example.spacexfanapp.UI.LauncherFragment
import com.example.spacexfanapp.UI.RocketsFragment
import com.example.spacexfanapp.UI.ViewPagerFragment

class ViewPagerAdapter(
    list: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    private val fragmentList = list

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                LauncherFragment()
            }
            1 -> {
                RocketsFragment()
            }
            2 -> {
                FavoritesFragment()
            }
            else -> ViewPagerFragment()
        }
    }
}