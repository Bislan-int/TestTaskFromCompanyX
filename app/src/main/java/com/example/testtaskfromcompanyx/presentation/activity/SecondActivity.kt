package com.example.testtaskfromcompanyx.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testtaskfromcompanyx.R
import com.example.testtaskfromcompanyx.databinding.ActivitySecondBinding
import com.example.testtaskfromcompanyx.presentation.fragment.PageOneFragment
import com.example.testtaskfromcompanyx.presentation.fragment.ProfileFragment

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchFragment(PageOneFragment.newInstance())
        navigation()
    }

    private fun navigation() {
        binding.bottomAppBar2.menu.findItem(R.id.home).setOnMenuItemClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentSecondaryContainer, PageOneFragment.newInstance())
                .addToBackStack(null)
                .commit()
            return@setOnMenuItemClickListener true
        }

        binding.bottomAppBar2.menu.findItem(R.id.profile).setOnMenuItemClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentSecondaryContainer, ProfileFragment())
                .addToBackStack(null)
                .commit()
            return@setOnMenuItemClickListener true
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentSecondaryContainer, fragment)
            .commit()
    }
}