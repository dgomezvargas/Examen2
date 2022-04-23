package com.examen2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.examen2.data.Examen2Database
import com.examen2.model.Examen2
import com.examen2.repository.Examen2Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Examen2ViewModel(application: Application)
    : AndroidViewModel(application) {

    val getAllData : LiveData<List<Examen2>>
    private val repository: Examen2Repository

    init{
        val examen2Dao = Examen2Database.getDatabase(application).examen2Dao()
        repository = Examen2Repository(examen2Dao)
        getAllData = repository.getAllData
    }

    fun addExamen2(examen2: Examen2){
        viewModelScope.launch(Dispatchers.IO) {repository.addExamen2(examen2)}
    }

    fun updateExamen2(examen2: Examen2){
        viewModelScope.launch(Dispatchers.IO) {repository.updateExamen2(examen2)}
    }

    fun deleteExamen2(examen2: Examen2){
        viewModelScope.launch(Dispatchers.IO) {repository.deleteExamen2(examen2)}
    }


}