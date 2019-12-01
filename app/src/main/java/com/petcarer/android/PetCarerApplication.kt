package com.petcarer.android

import com.petcarer.android.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PetCarerApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<PetCarerApplication> = DaggerAppComponent
        .builder()
        .application(this)
        .build()
}