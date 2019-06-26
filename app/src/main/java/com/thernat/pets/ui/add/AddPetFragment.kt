package com.thernat.pets.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thernat.pets.R
import com.thernat.pets.databinding.FragmentAddPetBinding
import com.thernat.pets.ui.AbstractPetFragment
import com.thernat.pets.ui.master.MasterViewModel
import com.thernat.pets.ui.master.adapter.PetsAdapter
import com.thernat.pets.utils.dialogs.SimpleDialogCreator
import javax.inject.Inject

/**
 * Created by m.rafalski on 24/06/2019.
 */
class AddPetFragment: AbstractPetFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding : FragmentAddPetBinding

    private lateinit var addPetViewModel: AddPetViewModel

    @Inject
    lateinit var dialogCreator: SimpleDialogCreator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_pet, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonAdd.setOnClickListener {addPetViewModel.addPet()  }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addPetViewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddPetViewModel::class.java)
            .also { binding.viewmodel = it }
            .apply {
                setEmptyEventObserver(loadingStartedCommand){ binding.isLoading = true}
                setEmptyEventObserver(wrongDataCommand){ dialogCreator.displayAlert(requireContext(),getString(R.string.dialog_input_data_wrong)) }
                loadingCompleteCommand.observe(viewLifecycleOwner,
                    Observer{ event ->
                        event.getContentIfNotHandled()?.let {result ->
                                dialogCreator.displayAlert(requireContext(),getResultMessage(result))
                        }
                        binding.isLoading = false
                    })
            }
    }

    private fun getResultMessage(result: Boolean) = if(result)getString(R.string.dialog_new_pet_added) else getString(R.string.dialog_new_pet_not_added)

}