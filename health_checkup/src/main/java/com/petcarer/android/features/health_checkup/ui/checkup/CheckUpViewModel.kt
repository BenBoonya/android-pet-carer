package com.petcarer.android.features.health_checkup.ui.checkup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.boonya.android.library_base.base.BaseViewModel
import com.boonya.android.library_base.data.livedata.SingleLiveEvent
import com.google.firebase.ml.vision.objects.FirebaseVisionObject
import com.otaliastudios.cameraview.PictureResult
import com.petcarer.android.features.health_checkup.domain.usecases.ExtractDataFromFrameUseCase
import com.petcarer.android.features.health_checkup.domain.usecases.GetFirebaseModelInterpreterUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckUpViewModel @Inject constructor(
    private val extractDataFromFrameUseCase: ExtractDataFromFrameUseCase,
    private val getFirebaseModelInterpreterUseCase: GetFirebaseModelInterpreterUseCase
) : BaseViewModel() {

    val _closeDialog = SingleLiveEvent<Any>()

    val _captureCamera = SingleLiveEvent<Any>()

    val checkUpResult = MutableLiveData<String>()

    val firebaseVisionObjects = ArrayList<FirebaseVisionObject>()

    fun onCloseClicked() {
        _closeDialog.call()
    }

    fun initilizeModelIntepreter() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val modelInterpreter = getFirebaseModelInterpreterUseCase()
            isLoading.postValue(false)
        }
    }

    fun onFabClicked() {
        viewModelScope.launch {
            isLoading.value = true
            repeat(10) {
                _captureCamera.call()
                delay(200)
            }
            isLoading.value = false
            processVisionObjects(firebaseVisionObjects)
        }
    }

    fun extractDataFromFrame(pictureResult: PictureResult) {
        extractDataFromFrameUseCase(
            pictureResult,
            this::onExtractDataSuccess,
            this::onExtractDataFailure
        )
    }

    fun onExtractDataSuccess(objects: List<FirebaseVisionObject>) {
        firebaseVisionObjects.addAll(objects)
    }

    fun onExtractDataFailure(message: String) {

    }

    fun processVisionObjects(objects: List<FirebaseVisionObject>) {
        //TODO Determining the check result
        checkUpResult.value = "Everything looks good!"
    }
}