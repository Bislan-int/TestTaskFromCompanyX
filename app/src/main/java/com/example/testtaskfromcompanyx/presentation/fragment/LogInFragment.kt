package com.example.testtaskfromcompanyx.presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskfromcompanyx.R
import com.example.testtaskfromcompanyx.databinding.FragmentLogInBinding
import com.example.testtaskfromcompanyx.presentation.TestTaskApplication
import com.example.testtaskfromcompanyx.presentation.viewmodel.LogInViewModel
import com.example.testtaskfromcompanyx.presentation.activity.SecondActivity
import com.example.testtaskfromcompanyx.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private lateinit var viewModel: LogInViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as TestTaskApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LogInViewModel::class.java]
        addTextChangeListeners()
        observeViewModel()
        binding.btLogIn.setOnClickListener {

            viewModel.validateInput(
                binding.etFirstName.text.toString(),
                binding.etPassword.text.toString()
            )

            Log.d("isUserExistsLogIn", "${viewModel.isTrueLogIn.value}")
            if (viewModel.isTrueLogIn.value == true) {
                val intent = Intent(activity, SecondActivity::class.java)
                requireActivity().startActivity(intent)
            }
        }
//        binding.btLogIn.setOnClickListener {
//            val intent = Intent(activity, SecondActivity::class.java)
//            requireActivity().startActivity(intent)
//        }
    }

//    override fun onStop() {
//        super.onStop()
//        requireActivity().finish()
//    }

    private fun observeViewModel() {
        viewModel.isUserExists.observe(viewLifecycleOwner) {
            val massage = if (it) {
                getString(R.string.error_input_first_name_or_password)
            } else {
                null
            }
            binding.etFirstName.error = massage
            binding.etPassword.error = massage
        }
    }

    private fun addTextChangeListeners() {
        binding.etFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInput()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInput()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }


    companion object {
        fun newInstance() = LogInFragment()
    }

}