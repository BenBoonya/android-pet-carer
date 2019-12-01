package com.petcarer.android.features.health_checkup.ui.checkup_list

import com.boonya.android.library_base.base.BaseViewModel
import com.boonya.android.library_base.data.livedata.SingleLiveEvent
import javax.inject.Inject

class CheckUpListViewModel @Inject constructor() : BaseViewModel() {

    val _navigateToCheckUp = SingleLiveEvent<Any>()

    val _navigateBack = SingleLiveEvent<Any>()

    fun onCheckUpClicked() {
        _navigateToCheckUp.call()
    }

    fun onCloseClicked() {
        _navigateBack.call()
    }

}