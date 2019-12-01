package com.boonya.android.library_base.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import com.boonya.android.library_base.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class ViewModelFragment<T : BaseViewModel> : DaggerFragment() {

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
                it?.let {
                    showErrorMessage(it)
                }
            })
        }
    }

    protected fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}