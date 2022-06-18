package com.mlinde.runner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val trail = intent.getSerializableExtra("trail") as? Trail

        if (savedInstanceState == null){
            val detailFragment = DetailFragment.newInstance(trail)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.crLayout, detailFragment)
                commit()
            }
        }
    }
}