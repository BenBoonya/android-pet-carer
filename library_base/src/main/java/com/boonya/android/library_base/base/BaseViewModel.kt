package com.boonya.android.library_base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()

    fun handleError(errorMessage: String?) {
        isLoading.value = false
        this.errorMessage.value = errorMessage
    }
}