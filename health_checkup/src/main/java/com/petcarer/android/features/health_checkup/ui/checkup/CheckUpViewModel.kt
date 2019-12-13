package com.petcarer.android.features.health_checkup.ui.checkup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.boonya.android.library_base.base.BaseViewModel
import com.boonya.android.library_base.data.livedata.SingleLiveEvent
import com.google.firebase.ml.custom.FirebaseModelInterpreter
import com.google.firebase.ml.vision.objects.FirebaseVisionObject
import com.otaliastudios.cameraview.PictureResult
import com.petcarer.android.features.health_checkup.domain.usecases.ConvertBitmapToByteBufferUseCase
import com.petcarer.android.features.health_checkup.domain.usecases.ExtractDataFromFrameUseCase
import com.petcarer.android.features.health_checkup.domain.usecases.GetFirebaseModelInterpreterUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckUpViewModel @Inject constructor(
    private val extractDataFromFrameUseCase: ExtractDataFromFrameUseCase,
    private val getFirebaseModelInterpreterUseCase: GetFirebaseModelInterpreterUseCase,
    private val convertBitmapToByteBufferUseCase: ConvertBitmapToByteBufferUseCase
) : BaseViewModel() {

    private var modelInterpreter: FirebaseModelInterpreter? = null

    init {
        initializeModelInterpreter()
    }

    val _closeDialog = SingleLiveEvent<Any>()

    val _captureCamera = SingleLiveEvent<Any>()

    val checkUpResult = MutableLiveData<String>()

    val firebaseVisionObjects = ArrayList<FirebaseVisionObject>()

    fun onCloseClicked() {
        _closeDialog.call()
    }

    private fun initializeModelInterpreter() {
        viewModelScope.launch {
            isLoading.postValue(true)
            modelInterpreter = getFirebaseModelInterpreterUseCase()
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