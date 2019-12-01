package com.petcarer.android.di.modules

import com.petcarer.android.features.health_checkup.ui.checkup_list.CheckUpListFragment
import com.petcarer.android.features.health_checkup.ui.health_checkup.HealthCheckUpFragment
import com.petcarer.android.features.health_checkup.ui.checkup.CheckupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun healthCheckUpFragment(): HealthCheckUpFragment

    @ContributesAndroidInjector
    abstract fun checkUpListFragment(): CheckUpListFragment

    @ContributesAndroidInjector
    abstract fun noseCheckUpFragment(): CheckupFragment

}