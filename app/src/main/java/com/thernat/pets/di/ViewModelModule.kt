package com.thernat.pets.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thernat.pets.ui.add.AddPetViewModel
import com.thernat.pets.ui.master.MasterViewModel
import com.thernat.pets.viewmodel.MVVMViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MasterViewModel::class)
    abstract fun bindMasterViewModel(masterViewModel: MasterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddPetViewModel::class)
    abstract fun bindAddPetViewModel(addPetViewModel: AddPetViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MVVMViewModelFactory): ViewModelProvider.Factory
}