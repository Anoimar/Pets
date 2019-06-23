package com.thernat.pets.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thernat.pets.vo.Pet

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Database(
    entities = [
        Pet::class],
    version = 1,
    exportSchema = false
)
abstract class MVVMDatabase  : RoomDatabase() {

    abstract fun petDao(): PetDao


}