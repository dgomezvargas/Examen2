package com.examen2.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.examen2.model.Examen2

@Database(entities = [Examen2::class], version = 1, exportSchema = false)

abstract class Examen2Database : RoomDatabase(){

    abstract fun examen2Dao() : Examen2Dao

    companion object{
        @Volatile
        private var INSTANCE: Examen2Database? = null

        fun getDatabase (context: android.content.Context) : Examen2Database{
            var instance = INSTANCE
            if (instance != null){
                return instance
            }
            synchronized(this){
                val basedatos = Room.databaseBuilder(
                    context.applicationContext,
                    Examen2Database::class.java,
                    "examen2_database"
                ).build()
                INSTANCE = basedatos
                return basedatos
            }
        }
    }

}