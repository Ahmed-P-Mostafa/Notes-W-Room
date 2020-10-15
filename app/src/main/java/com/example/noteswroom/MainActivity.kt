package com.example.noteswroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),NotesAdapter.OnNoteClickListener {
            var adapter=NotesAdapter(this, null)
            lateinit var list :MutableList<Note>

    override fun onStart() {
        super.onStart()

         list = NotesDatabase.getInstance(this).notesDao().viewAllNotes()

        adapter.dataChanged(list)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes_recyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        //adapter = NotesAdapter(this,list)
        notes_recyclerview.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
        adapter.setOnLayoutClickListener(this)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                var temp = adapter.list?.get(viewHolder.adapterPosition)

                NotesDatabase.getInstance(this@MainActivity).notesDao().deleteNote(adapter.list?.get(viewHolder.adapterPosition))
                list.removeAt(viewHolder.adapterPosition)
                adapter.dataChanged(list)

                //noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()

                Snackbar.make(viewHolder.itemView,"Undo deleted",Snackbar.LENGTH_LONG).setAction("Undo") {
                    if (temp != null) {
                        NotesDatabase.getInstance(this@MainActivity).notesDao().addNote(temp)
                        //TODO complete this error
                        if (list.size<viewHolder.adapterPosition){
                            list.add(temp)
                        }else list.add(viewHolder.adapterPosition, temp)

                        adapter.dataChanged(list)
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                        //adapter.notifyDataSetChanged()
                    }
                }.setActionTextColor(resources.getColor(R.color.colorPrimaryDark))
                    .setTextColor(resources.getColor(android.R.color.white)).show()


            }
        }).attachToRecyclerView(notes_recyclerview)
    }
    override fun OnNoteClicked(note: Note, position: Int) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("id", note.id)
        intent.putExtra("title", note.title)
        intent.putExtra("description", note.description)
        intent.putExtra("date", note.date)
        startActivity(intent)
    }
}