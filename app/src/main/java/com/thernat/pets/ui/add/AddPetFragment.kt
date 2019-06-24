package com.thernat.pets.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thernat.pets.R
import com.thernat.pets.databinding.FragmentAddPetBinding
import com.thernat.pets.ui.AbstractPetFragment
import com.thernat.pets.ui.master.adapter.PetsAdapter
import javax.inject.Inject

/**
 * Created by m.rafalski on 24/06/2019.
 */
class AddPetFragment: AbstractPetFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding : FragmentAddPetBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_pet, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}