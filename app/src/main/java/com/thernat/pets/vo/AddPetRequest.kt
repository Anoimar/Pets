package com.thernat.pets.vo

/**
 * Created by m.rafalski on 24/06/2019.
 */
data class AddPetRequest(val type: String, val age: Int, val name: String, val owner: String? = null)