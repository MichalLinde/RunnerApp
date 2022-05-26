package com.mlinde.runner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), TrailAdapter.TrailListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onItemClicked(trail: Trail) {
        Toast.makeText(applicationContext, trail.name, Toast.LENGTH_SHORT).show()
    }
}