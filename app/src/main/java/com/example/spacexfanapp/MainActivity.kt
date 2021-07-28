package com.example.spacexfanapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.spacexfanapp.database.AppDatabase
import com.example.spacexfanapp.databinding.ActivityMainBinding
import com.example.spacexfanapp.viewmodel.RocketViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    private lateinit var binding: ActivityMainBinding
    private val rocketViewModel : RocketViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        /*
       val db:AppDatabase = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"favoriteEntity")
           .allowMainThreadQueries()
           .fallbackToDestructiveMigration()
           .build()
        */

        for(t in AppDatabase.getDatabase(applicationContext).favoriteDao().getAll()){
           Log.e("SRC","bb : "+t.name);
        }

       // tabLayout = findViewById(R.id.tabLayout)
       // viewPager = findViewById(R.id.viewPager)

       // tabCreated()

         //Crashlytic Test Code

        /*
        val crashButton = Button(this)
        crashButton.text = "Crash!"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }
        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        */

    }

    /*
    fun tabCreated(){

        tabLayout.addTab(tabLayout.newTab().setText("Launcher"))
        tabLayout.addTab(tabLayout.newTab().setText("Rockets"))
        tabLayout.addTab(tabLayout.newTab().setText("Favorites"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabsAdapter(this,supportFragmentManager,tabLayout.tabCount)

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
    */

}