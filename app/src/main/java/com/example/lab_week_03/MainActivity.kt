package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

interface CoffeeListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), CoffeeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize fragments if this is the first creation
        if (savedInstanceState == null) {
            setupFragments()
        }
    }

    private fun setupFragments() {
        // Add ListFragment to the container
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_list, ListFragment.newInstance("param1", "param2"))
            .commit()

        // Add DetailFragment to the container
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_detail, DetailFragment.newInstance("param1", "param2"))
            .commit()
    }

    override fun onSelected(id: Int) {
        val detailFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_detail) as? DetailFragment
        detailFragment?.setCoffeeData(id)
    }
}