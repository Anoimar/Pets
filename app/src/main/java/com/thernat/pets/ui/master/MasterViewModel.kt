package com.thernat.pets.ui.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thernat.pets.Event
import com.thernat.pets.exceptions.BasicException
import com.thernat.pets.repository.MVVMRepository
import com.thernat.pets.vo.Pet
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by m.rafalski on 07/06/2019.
 */
class MasterViewModel @Inject constructor(private val repository: MVVMRepository) : ViewModel() {

    private var getPetsJob : Job? = null

    private val _loadingStartedCommand = MutableLiveData<Event<Unit>>()
    val loadingStartedCommand: LiveData<Event<Unit>>
        get() = _loadingStartedCommand

    private val _loadingCompleteCommand = MutableLiveData<Event<Unit>>()
    val loadingCompleteCommand: LiveData<Event<Unit>>
        get() = _loadingCompleteCommand

    private val _pet: MutableLiveData<List<Pet>> = MutableLiveData()
    val pet: LiveData<List<Pet>>
        get() = _pet

    fun start(){
        _loadingStartedCommand.value = Event(Unit)
        getPetsJob = CoroutineScope(Dispatchers.IO).launch {
                try {
                    val pets = repository.getPets()
                    withContext(Dispatchers.Main) {
                        _pet.value = pets
                    }
                } catch (e: BasicException) {
                    Timber.e(e)
                } finally {
                    //Hide loading, unlock locked options
                    _loadingCompleteCommand.postValue(Event(Unit))

                }
        }
    }

    override fun onCleared() {
        getPetsJob?.cancel()
    }
}