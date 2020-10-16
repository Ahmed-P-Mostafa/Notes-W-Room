package com.example.noteswroom

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        var title: String? = ""
        var description: String? = ""
        var date: String? = ""
        val id:Int
        val position :Int


        if (intent.hasExtra("title")) {
            position = intent.getIntExtra("position",0)
            id = intent.getIntExtra("id",0)
            title = intent.getStringExtra("title")
            date = intent.getStringExtra("date")
            description = intent.getStringExtra("description")

            title_TV.setText(title)
            description_TV.setText(description)
            save_btn.setOnClickListener {
                val note=Note(id = id,title = title_TV.text.toString(),description = description_TV.text.toString(),date = date)

                NotesDatabase.getInstance(this).notesDao().updateNote(note)
                Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                finish()
            }

        } else {

            save_btn.setOnClickListener {

                if (description_TV.text != null) {

                    val title_text = title_TV.text.toString()
                    val description_text = description_TV.text.toString()

                    NotesDatabase.getInstance(this).notesDao().addNote(Note( title = title_text, description = description_text,date =  ""))


                    Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "can't save empty Note", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}