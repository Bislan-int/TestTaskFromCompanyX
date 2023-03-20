package com.example.testtaskfromcompanyx.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testtaskfromcompanyx.R
import com.example.testtaskfromcompanyx.databinding.ActivityMainBinding
import com.example.testtaskfromcompanyx.presentation.fragment.LogInFragment
import com.example.testtaskfromcompanyx.presentation.fragment.PageOneFragment
import com.example.testtaskfromcompanyx.presentation.fragment.SignInFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val intent = Intent(this, SecondActivity::class.java)
//            this.startActivity(intent)
        launchFragment(SignInFragment.newInstance())
    }

//    override fun onEditingFinished() {
//        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//        supportFragmentManager.popBackStack()
//    }

//    fun onFinish() {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentMainContainer, LogInFragment.newInstance())
//            .commit()
//    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMainContainer, fragment)
            .commit()
    }

}