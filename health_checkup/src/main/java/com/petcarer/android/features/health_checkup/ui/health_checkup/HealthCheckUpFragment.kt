package com.petcarer.android.features.health_checkup.ui.health_checkup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.boonya.android.library_base.base.ViewModelFragment
import com.petcarer.android.features.health_checkup.databinding.HealthCheckupFragmentBinding

class HealthCheckUpFragment : ViewModelFragment<HealthCheckUpViewModel>() {

    private lateinit var binding: HealthCheckupFragmentBinding

    private val navController by lazy { findNavController() }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(HealthCheckUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HealthCheckupFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun attachObserver() {
        super.attachObserver()

        with(viewModel) {
            _navigateTpHealthCheckUpList.observe(viewLifecycleOwner, Observer {
                navigateToHealthCheckUpList()
            })
        }
    }

    private fun navigateToHealthCheckUpList() {
        val directions =
            HealthCheckUpFragmentDirections.actionHealthCheckUpFragmentToCheckUpListFragment()
        navController.navigate(directions)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}