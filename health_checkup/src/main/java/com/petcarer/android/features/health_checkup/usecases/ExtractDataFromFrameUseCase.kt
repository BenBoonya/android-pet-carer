package com.petcarer.android.features.health_checkup.usecases

import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObject
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions
import com.otaliastudios.cameraview.PictureResult
import javax.inject.Inject

class ExtractDataFromFrameUseCase @Inject constructor() {

    operator fun invoke(
        frame: PictureResult,
        onSuccess: (List<FirebaseVisionObject>) -> Unit,
        onFailure: (String) -> Unit

    ) {
        val options = FirebaseVisionObjectDetectorOptions.Builder()
            .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()  //Add this if you want to detect multiple objects at once
            .enableClassification()  // Add this if you want to classify the detected objects into categories
            .build()

        val objectDetector = FirebaseVision.getInstance().getOnDeviceObjectDetector(options)

        frame.toBitmap {
            it?.let {
                objectDetector.processImage(FirebaseVisionImage.fromBitmap(it))
                    .addOnSuccessListener {
                        onSuccess(it)
                    }
                    .addOnFailureListener {
                        onFailure(it.message ?: "")
                    }
            }
        }
    }
}