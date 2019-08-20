package com.tdm2serie2ex4.master.projet_tdm2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tdm2serie2ex4.master.projet_tdm2.Model.Annonce

@Database(
        entities = [Annonce::class],
        version = 1,
        exportSchema = false
)
abstract class DataBase : RoomDatabase(){
    abstract fun annonceDao(): AnnonceDao


    companion object {
        @Volatile private var instance: DataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                DataBase::class.java, "annonce.db")
                .allowMainThreadQueries()
                .build()
    }
}