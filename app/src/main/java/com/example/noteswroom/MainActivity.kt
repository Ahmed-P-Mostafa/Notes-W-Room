package com.example.noteswroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),NotesAdapter.OnNoteClickListener {
            var adapter=NotesAdapter(this,null)

    override fun onStart() {
        super.onStart()
        val list = NotesDatabase.getInstance(this).notesDao().viewAllNotes()
        adapter.dataChanged(list)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes_recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        //adapter = NotesAdapter(this,list)
        notes_recyclerview.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
        }
        adapter.setOnLayoutClickListener(this)
    }
    override fun OnNoteClicked(note: Note, position: Int) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("position",position)
        intent.putExtra("id",note.id)
        intent.putExtra("title", note.title)
        intent.putExtra("description", note.description)
        intent.putExtra("date", note.date)
        startActivity(intent)
    }
}