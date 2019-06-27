package com.thernat.pets.ui.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thernat.pets.R
import javax.inject.Inject
import com.thernat.pets.databinding.FragmentMasterBinding
import com.thernat.pets.ui.AbstractPetFragment
import com.thernat.pets.ui.master.adapter.PetsAdapter
import com.thernat.pets.utils.image.ImageUrlProvider

/**
 * Created by m.rafalski on 07/06/2019.
 */
class MasterFragment: AbstractPetFragment()  {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var masterViewModel: MasterViewModel

    private lateinit var binding : FragmentMasterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_master, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvPets.adapter = PetsAdapter(requireContext(), arrayListOf(), ImageUrlProvider())
        binding.fabGoToAddPet.setOnClickListener{navController().navigate(R.id.action_master_fragment_to_add_pet_fragment)}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        masterViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MasterViewModel::class.java).also { binding.viewmodel = it }
        initMasterFragment()
    }

    private fun initMasterFragment() {
        masterViewModel.apply {
            setEmptyEventObserver(loadingStartedCommand){ binding.isLoading = true}
            setEmptyEventObserver(loadingCompleteCommand){ binding.isLoading = false}
        }
        masterViewModel.start()

    }
}