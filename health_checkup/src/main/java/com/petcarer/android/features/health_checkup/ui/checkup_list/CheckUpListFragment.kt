package com.petcarer.android.features.health_checkup.ui.checkup_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.boonya.android.library_base.base.ViewModelFragment
import com.petcarer.android.features.health_checkup.databinding.CheckupListFragmentBinding

class CheckUpListFragment : ViewModelFragment<CheckUpListViewModel>() {

    private lateinit var binding: CheckupListFragmentBinding

    private val navController: NavController by lazy { findNavController() }

    override fun initViewModel() {
        viewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            ).get(CheckUpListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CheckupListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun attachObserver() {
        super.attachObserver()

        with(viewModel) {
            _navigateToCheckUp.observe(viewLifecycleOwner, Observer {
                navigateToHealthCheckUp()
            })

            _navigateBack.observe(viewLifecycleOwner, Observer {
                activity?.finish()
            })
        }
    }

    private fun navigateToHealthCheckUp() {
        val directions =
            CheckUpListFragmentDirections.actionCheckUpListFragmentToNoseCheckupFragment()
        navController.navigate(directions)
    }

}
