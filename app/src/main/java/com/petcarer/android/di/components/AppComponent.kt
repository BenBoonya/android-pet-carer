package com.petcarer.android.di.components

import android.app.Application
import com.petcarer.android.PetCarerApplication
import com.petcarer.android.di.modules.ActivityBindingModule
import com.petcarer.android.di.modules.FirebaseModule
import com.petcarer.android.di.modules.FragmentBindingModule
import com.petcarer.android.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        FirebaseModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        AndroidSupportInjectionModule::class
    ]
)

interface AppComponent : AndroidInjector<PetCarerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}