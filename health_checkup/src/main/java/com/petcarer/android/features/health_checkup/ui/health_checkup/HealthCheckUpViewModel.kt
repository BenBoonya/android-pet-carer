package com.petcarer.android.features.health_checkup.ui.health_checkup

import com.boonya.android.library_base.base.BaseViewModel
import com.boonya.android.library_base.data.livedata.SingleLiveEvent
import javax.inject.Inject

class HealthCheckUpViewModel @Inject constructor() : BaseViewModel() {


    val _navigateTpHealthCheckUpList = SingleLiveEvent<Any>()

    fun onFabClicked() {
        _navigateTpHealthCheckUpList()
    }

}