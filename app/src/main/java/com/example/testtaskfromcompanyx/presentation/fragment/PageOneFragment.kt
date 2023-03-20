package com.example.testtaskfromcompanyx.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskfromcompanyx.databinding.FragmentPageOneBinding
import com.example.testtaskfromcompanyx.presentation.TestTaskApplication
import com.example.testtaskfromcompanyx.presentation.adapter.PageOneAdapter
import com.example.testtaskfromcompanyx.presentation.adapterdelegates.PageOneScreenDelegates
import com.example.testtaskfromcompanyx.presentation.viewmodel.PageOneViewModel
import com.example.testtaskfromcompanyx.presentation.viewmodel.ViewModelFactory
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PageOneFragment : Fragment() {

    private lateinit var binding: FragmentPageOneBinding

    private lateinit var viewModel: PageOneViewModel
    private val adapter = PageOneAdapter()

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
        binding = FragmentPageOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[PageOneViewModel::class.java]

        initRecycler()
        searchView()
    }

    private fun initRecycler() {
        with(binding) {
            rvCategory.adapter = ListDelegationAdapter(
                PageOneScreenDelegates.categoryItemDelegate
            ).apply {
                viewModel.getCategory.observe(viewLifecycleOwner) {
                    items = it
                }
            }

            rvMainVertical.adapter = adapter
            viewModel.getItems.observe(viewLifecycleOwner) {
                adapter.items = it
            }
        }
    }

    private fun searchView() {
        with(binding) {
            rvMainVertical.adapter = adapter
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText == "") {
                        viewModel.getItems.observe(viewLifecycleOwner) {
                            adapter.items = it
                        }
                        return true
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getSearchItems(newText)
                        viewModel.getSearchItems.observe(viewLifecycleOwner) {
                            adapter.items = it
                        }
                    }
                    return true
                }
            })
        }
    }

    companion object {
        fun newInstance() = PageOneFragment()
    }
}