package com.thernat.pets.vo

import androidx.room.Entity

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Entity(primaryKeys = ["id"])
data class Pet(val id: Int, val type: String, val age: Int,val owner: String?, val name: String)