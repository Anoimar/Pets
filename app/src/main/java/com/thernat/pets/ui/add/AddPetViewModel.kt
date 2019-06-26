package com.thernat.pets.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thernat.pets.Event
import com.thernat.pets.exceptions.BasicException
import com.thernat.pets.repository.MVVMRepository
import com.thernat.pets.vo.AddPetRequest
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by m.rafalski on 24/06/2019.
 */
class AddPetViewModel @Inject constructor(private val repository: MVVMRepository) : ViewModel(){


    private var addPetJob : Job? = null

    private val _addingStartedCommand = MutableLiveData<Event<Unit>>()
    val loadingStartedCommand: LiveData<Event<Unit>>
        get() = _addingStartedCommand

    private val _addingCompleteCommand = MutableLiveData<Event<Boolean>>()
    val loadingCompleteCommand: LiveData<Event<Boolean>>
        get() = _addingCompleteCommand

    private val _wrongDataCommand = MutableLiveData<Event<Unit>>()
    val wrongDataCommand: LiveData<Event<Unit>>
        get() = _wrongDataCommand

    val nameLiveData = MutableLiveData<String>()
    val ageLiveData = MutableLiveData<String>()
    val ownerLiveData =  MutableLiveData<String?>()
    val typeLiveData = MutableLiveData<String>()


    internal fun addPet(){
        val name= nameLiveData.value
        val type= typeLiveData.value
        val age = ageLiveData.value
        val owner = ownerLiveData.value
        if(name != null && !age.isNullOrBlank() && type != null){
           tryAddingPet(AddPetRequest(type,Integer.valueOf(age),name,owner))
        } else {
            _wrongDataCommand.value = Event(Unit)
        }
    }

    private fun tryAddingPet(addPetRequest: AddPetRequest) {
        _addingStartedCommand.value = Event(Unit)
        var result = false
        addPetJob = CoroutineScope(Dispatchers.IO).launch {
            try {
               repository.addPet(addPetRequest)
                result = true
            } catch (e: BasicException) {
                Timber.e(e)
            } finally {
                //Hide loading, unlock locked options
                _addingCompleteCommand.postValue(Event(result))
            }
        }
    }

    override fun onCleared() {
        addPetJob?.cancel()
    }

}