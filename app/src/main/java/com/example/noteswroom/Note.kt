package com.example.noteswroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note(@PrimaryKey(autoGenerate = true)val id:Int?=null
                , @ColumnInfo var title: String?=null
                , @ColumnInfo var description: String?=null
                , @ColumnInfo var date: String?)