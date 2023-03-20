package com.example.testtaskfromcompanyx.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testtaskfromcompanyx.R
import com.example.testtaskfromcompanyx.databinding.FragmentProfileBinding
import com.example.testtaskfromcompanyx.presentation.activity.MainActivity
import com.example.testtaskfromcompanyx.presentation.activity.SecondActivity


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contentScrolling.tvLogOut.setOnClickListener {
//            val intent = Intent(this.requireContext(), MainActivity::class.java)
//            this.startActivity(intent)
//            MainActivity().onFinish()
            requireActivity().finish()
        }
    }
}