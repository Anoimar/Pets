package com.thernat.pets.di

import com.thernat.pets.ui.add.AddPetFragment
import com.thernat.pets.ui.master.MasterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMasterFragment(): MasterFragment

    @ContributesAndroidInjector
    abstract fun contributePetFragment(): AddPetFragment

}
