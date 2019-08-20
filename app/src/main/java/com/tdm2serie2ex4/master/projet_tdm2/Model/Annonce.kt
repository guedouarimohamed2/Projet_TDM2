package com.tdm2serie2ex4.master.projet_tdm2.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "annonce")
data class Annonce (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "nom") var nom : String,
    @ColumnInfo(name = "type") var type : String,
    @ColumnInfo(name = "wilaya") var wilaya : String
/*    @ColumnInfo(name = "description") var description : String,
    @ColumnInfo(name = "telephone") var telephone : String,
    @ColumnInfo(name = "email") var email : String,
    @ColumnInfo(name = "date") var date_depot : String*/
    )


