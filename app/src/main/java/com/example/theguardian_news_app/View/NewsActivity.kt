package com.example.theguardian_news_app.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.theguardian_news_app.R
import com.example.theguardian_news_app.databinding.ActivityNewsBinding
import com.example.theguardian_news_app.View.fragments.headlinesFragment
import com.example.theguardian_news_app.View.fragments.profileFragment
import com.example.theguardian_news_app.View.fragments.savedFragment
import com.example.theguardian_news_app.View.fragments.searchFragment

class newsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(headlinesFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileIcon -> replaceFragment(profileFragment())
                R.id.headlinesIcon -> replaceFragment(headlinesFragment())
                R.id.savedIcon -> replaceFragment(savedFragment())
                R.id.searchIcon -> replaceFragment(searchFragment())

                else -> { } //optional
            }
            true
        }
    }

     private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment, fragment)
        fragmentTransaction.commit()

    }
}