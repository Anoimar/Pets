package com.thernat.pets.api

import com.thernat.pets.vo.AddPetRequest
import com.thernat.pets.vo.Pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by m.rafalski on 07/06/2019.
 */
interface ApiService {

    @GET("animals")
    suspend fun getPetList(): Response<List<Pet>>

    @POST("animal")
    suspend fun addPet(@Body addPetRequest: AddPetRequest): Response<Pet>
}