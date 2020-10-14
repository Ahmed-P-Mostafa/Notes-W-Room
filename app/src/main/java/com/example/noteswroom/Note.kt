package com.example.noteswroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(@PrimaryKey(autoGenerate = true) val id:Int
                , @ColumnInfo(name ="Note Title") val title:String
                , @ColumnInfo(name = "Note Description")val description:String
                , @ColumnInfo(name = "Note created at")val date:String) {

}