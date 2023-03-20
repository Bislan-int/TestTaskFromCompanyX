package com.example.testtaskfromcompanyx.presentation.fragment

import android.content.Context
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
import com.example.testtaskfromcompanyx.databinding.FragmentSignInBinding
import com.example.testtaskfromcompanyx.presentation.TestTaskApplication
import com.example.testtaskfromcompanyx.presentation.viewmodel.SignInViewModel
import com.example.testtaskfromcompanyx.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel

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
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
//        viewModel.checkUser("-1")
        addTextChangeListeners()
        observeViewModel()


        //{viewModel.isUserExists.value возвращает null, а не Boolean!
        binding.btSignIn.setOnClickListener {
            viewModel.addUser(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etEmail.text.toString()
            )
            Log.d("validInputValues", "${viewModel.isUserExists.value}")
            Log.d("validInputValues2", "${viewModel.validInputValues.value}")
            if (viewModel.validInputValues.value == true) {
               launchFragment(LogInFragment.newInstance())
            }

//            viewModel.isUserExists.observe(viewLifecycleOwner) {
//                Log.d("isUserExistsSign", "$it")
//            }
        }

        binding.textButtonLogIn.setOnClickListener {
            launchFragment(LogInFragment.newInstance())
        }
    }

    private fun launchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMainContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun observeViewModel() {
//        viewModel.errorInputFirstName.observe(viewLifecycleOwner) {
//            val massage = if (it) {
//                getString(R.string.error_input_first_name)
//            } else {
//                null
//            }
//            binding.etFirstName.error = massage
//        }
        viewModel.errorInputLastName.observe(viewLifecycleOwner) {
            val massage = if (it) {
                getString(R.string.error_input_last_name)
            } else {
                null
            }
            binding.etLastName.error = massage
        }
        viewModel.errorInputEmail.observe(viewLifecycleOwner) {
            val massage = if (it) {
                getString(R.string.error_input_email)
            } else {
                null
            }
            binding.etEmail.error = massage
        }
        viewModel.isUserExists.observe(viewLifecycleOwner) { isUserExists ->
            Log.d("SignIn", "$isUserExists")
            viewModel.errorInputFirstName.observe(viewLifecycleOwner) { errorInputFirstName ->
                val massage = if (errorInputFirstName) {
                    getString(R.string.error_input_first_name)
                } else if (isUserExists && errorInputFirstName == false) {
                    getString(R.string.user_exists)
                } else {
                    null
                }
                binding.etFirstName.error = massage
            }
        }
    }

    private fun addTextChangeListeners() {
        binding.etFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputFirstName()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputLastName()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputEmail()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}