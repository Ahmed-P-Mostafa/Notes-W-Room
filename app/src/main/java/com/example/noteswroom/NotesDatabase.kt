package com.example.noteswroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class),version = 1,exportSchema = false)
abstract class NotesDatabase :RoomDatabase() {

    abstract fun notesDao():NotesDao


    companion object{

        private val DB_NAME = "Notes Database"

        fun getInstance(context: Context):NotesDatabase{

            val db = Room.databaseBuilder(
                context,
                NotesDatabase::class.java, DB_NAME
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            return  db
        }
    }

}