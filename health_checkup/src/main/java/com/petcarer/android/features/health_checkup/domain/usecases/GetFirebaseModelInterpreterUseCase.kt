package com.petcarer.android.features.health_checkup.domain.usecases

import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.custom.FirebaseCustomRemoteModel
import com.google.firebase.ml.custom.FirebaseModelInterpreter
import com.google.firebase.ml.custom.FirebaseModelInterpreterOptions
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GetFirebaseModelInterpreterUseCase @Inject constructor() {

    suspend operator fun invoke(): FirebaseModelInterpreter? {
        return suspendCoroutine { cont ->

            val conditions =
                FirebaseModelDownloadConditions.Builder().build()

            val remoteModel =
                FirebaseCustomRemoteModel.Builder("pre-trained").build()
            val manager = FirebaseModelManager.getInstance()
            manager.download(remoteModel, conditions).addOnCompleteListener {
                if (!it.isSuccessful) cont.resumeWithException(
                    RuntimeException("Remote model failed to download", it.exception)
                )

                val remoteInterpreter = FirebaseModelInterpreter.getInstance(
                    FirebaseModelInterpreterOptions.Builder(remoteModel).build()
                )!!

                cont.resume(remoteInterpreter)
            }
        }
    }
}