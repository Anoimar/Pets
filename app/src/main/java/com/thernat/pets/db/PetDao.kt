package com.thernat.pets.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thernat.pets.vo.Pet

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pet: Pet)

    @Query("SELECT * FROM pet")
    fun getPets(): List<Pet>
}