package com.examen2.repository

import androidx.lifecycle.LiveData
import com.examen2.data.Examen2Dao
import com.examen2.model.Examen2

class Examen2Repository(private val examen2Dao: Examen2Dao) {

    val getAllData : LiveData<List<Examen2>> = examen2Dao.getAllData()

    suspend fun addExamen2(examen2: Examen2){
        examen2Dao.addExamen2(examen2)
    }

    suspend fun updateExamen2(examen2: Examen2){
        examen2Dao.updateExamen2(examen2)
    }

    suspend fun deleteExamen2(examen2: Examen2){
        examen2Dao.deleteExamen2(examen2)
    }
}