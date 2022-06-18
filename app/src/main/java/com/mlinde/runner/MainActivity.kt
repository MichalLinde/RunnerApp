package com.mlinde.runner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    //private val difficultyListFragment = DifficultyListFragment()
    //private val cityListFragment = CityListFragment()
    //private val mainListFragment = MainListFragment()
    //private val homeFragment = HomeFragment()

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    //private val viewModel: MainActivityViewModel by viewModels {MainActivityViewModel.Factory(homeFragment)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //replaceFragment(viewModel.fragment)

        //val navBar = findViewById<BottomNavigationView>(R.id.navBar)
        viewPager = findViewById(R.id.viewPager)
        //appBarLayout = findViewById(R.id.appBarLayout)
        tabs = findViewById(R.id.tabs)

        setUpTabs()

        //val viewPager: ViewPager2 = findViewById(R.id.fragment_container)

//        val fragments : ArrayList<Fragment> = arrayListOf(
//            mainListFragment,
//            homeFragment,
//            difficultyListFragment,
//            cityListFragment
//        )
        //val adapter = ViewPagerAdapter(fragments, this)
        //viewPager.adapter = adapter

//        navBar.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.cityItem -> replaceFragment(cityListFragment)
//                R.id.difficultyItem -> replaceFragment(difficultyListFragment)
//                R.id.mainListItem -> replaceFragment(mainListFragment)
//                R.id.homeItem -> replaceFragment(homeFragment)
//            }
//            true
//        }

    }

//    private fun replaceFragment(fragment: Fragment){
//        viewModel.fragment = fragment
//        if (fragment != null){
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, fragment)
//            transaction.commit()
//        }
//
//    }
    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "")
        adapter.addFragment(MainListFragment(), "")
        adapter.addFragment(DifficultyListFragment(), "")
        adapter.addFragment(CityListFragment(), "")
        viewPager.adapter = adapter
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.home_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.list_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.difficulty_24)
        tabs.getTabAt(3)!!.setIcon(R.drawable.place_24)

    }

}


