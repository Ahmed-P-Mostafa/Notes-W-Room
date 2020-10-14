package com.example.noteswroom

import androidx.room.*

@Dao
interface NotesDao {

    @Insert
    fun addNote(note:Note)

    @Update
    fun updateNote(note: Note)


    @Query("delete from note where id =:id")
    fun deleteNote(id:Int)

    @Query("select * From Note")
    fun viewAllNotes():ArrayList<Note>
}