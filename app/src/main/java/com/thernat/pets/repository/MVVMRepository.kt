package com.thernat.pets.repository

import com.thernat.pets.api.ApiService
import com.thernat.pets.db.PetDao
import com.thernat.pets.exceptions.BasicException
import com.thernat.pets.vo.AddPetRequest
import com.thernat.pets.vo.Pet
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Singleton
class MVVMRepository @Inject constructor(private val apiService: ApiService,private val petDao: PetDao) {

    private var cachedPetsList: List<Pet> = listOf()
    private var isCacheDirty = false

    suspend fun getPets(): List<Pet> {
        if(!isCacheDirty){
            if(!cachedPetsList.isNullOrEmpty()){
                return cachedPetsList
            }
            val petsFromDatabase = useDataFromDatabase()
            if(petsFromDatabase.isNotEmpty()){
                return petsFromDatabase
            }
        }
        return useDataFromRemote()
    }

    suspend fun addPet(addPet: AddPetRequest){
        try {
        if (apiService.addPet(addPet).isSuccessful){
            isCacheDirty = true
            return
        }
        } catch (e: Exception) {
            Timber.e(e)
        }
        throw BasicException()

    }

    private fun useDataFromDatabase(): List<Pet> {
        Timber.i("Trying to use element saved in database")
        return petDao.getPets().also {
            cachedPetsList = it
        }

    }

    private suspend fun useDataFromRemote(): List<Pet> {
        Timber.i("Trying to use data from remote")
        try {
            val response = apiService.getPetList()
            if(response.isSuccessful){
                response.body()?.let {pets ->
                    cachedPetsList = pets
                    pets.forEach { petDao.insert(it) }
                    isCacheDirty = false
                    return pets
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        //TODO more advanced error handling
        throw BasicException()
    }
}