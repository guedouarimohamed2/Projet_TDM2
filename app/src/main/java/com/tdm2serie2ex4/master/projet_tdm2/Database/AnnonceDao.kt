package com.tdm2serie2ex4.master.projet_tdm2.Database

import androidx.room.*
import com.tdm2serie2ex4.master.projet_tdm2.Model.Annonce

@Dao
interface AnnonceDao
{

    @Query("SELECT * FROM annonce")
    fun getAll(): List<Annonce>



    @Insert
    fun insertAll(vararg annonce: Annonce)

    @Delete
    fun delete(annonce: Annonce)

    @Update
    fun update(vararg annonce: Annonce)


}