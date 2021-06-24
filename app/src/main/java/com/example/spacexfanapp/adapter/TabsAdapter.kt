package com.example.spacexfanapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.spacexfanapp.UI.FavoritesFragment
import com.example.spacexfanapp.UI.LauncherFragment
import com.example.spacexfanapp.UI.RocketsFragment

@Suppress("DEPRECATION")
internal class TabsAdapter (
    var context: Context,
    var fm: FragmentManager,
    var totalTabs: Int = 0

) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int):Fragment {

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
            else -> getItem(position)
        }
    }

    override fun getCount() : Int {
        return totalTabs
    }
}
