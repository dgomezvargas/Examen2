package com.examen2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.examen2.model.Examen2

@Dao

interface Examen2Dao {

    @Query("select * from EXAMEN2")
    fun getAllData(): LiveData<List<Examen2>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExamen2(examen2: Examen2)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateExamen2(examen2: Examen2)

    @Delete
    suspend fun deleteExamen2(examen2: Examen2)
}