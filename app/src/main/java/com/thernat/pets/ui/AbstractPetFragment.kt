package com.thernat.pets.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.thernat.pets.Event
import com.thernat.pets.di.Injectable

/**
 * Created by m.rafalski on 24/06/2019.
 */
abstract class AbstractPetFragment: Fragment(), Injectable {

    fun navController() = findNavController()

    //Helper function used to observe events from view model
    protected fun setEmptyEventObserver(liveData: LiveData<Event<Unit>>, action: () -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { action()}
        })
    }


}