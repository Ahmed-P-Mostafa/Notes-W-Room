package com.example.noteswroom

import androidx.room.*

@Dao
interface NotesDao {

    @Insert
    fun addNote(note:Note)

    @Update
    fun updateNote(note: Note)


    @Delete
    fun deleteNote(note: Note?)

    @Query("SELECT * FROM Note")
    fun viewAllNotes():MutableList<Note>
}