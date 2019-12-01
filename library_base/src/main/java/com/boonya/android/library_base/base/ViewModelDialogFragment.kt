package com.boonya.android.library_base.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import com.boonya.android.library_base.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

abstract class ViewModelDialogFragment<T : BaseViewModel> : DaggerDialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<T>

    protected lateinit var viewModel: T

    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        attachObserver()
    }

    @CallSuper
    protected open fun attachObserver() {
        with(viewModel) {
            errorMessage.observe(viewLifecycleOwner, Observer {

            })
        }
    }

    protected fun showErrorMessage(message: String) {
        //TODO show error message to UI
    }
}