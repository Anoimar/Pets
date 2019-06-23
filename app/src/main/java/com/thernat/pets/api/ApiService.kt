package com.thernat.pets.api

import com.thernat.pets.vo.Pet
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by m.rafalski on 07/06/2019.
 */
interface ApiService {

    @GET("animals")
    suspend fun getPetList(): Response<List<Pet>>
}