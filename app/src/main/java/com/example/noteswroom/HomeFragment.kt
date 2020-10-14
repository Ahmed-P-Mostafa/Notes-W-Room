package com.example.noteswroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.FragmentTransitionSupport
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),NotesAdapter.OnNoteClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialize notes in List By calling database instance.
       // val note = Note(1,"title","description","today")

       // NotesDatabase.getInstance(view.context).notesDao().addNote(Note(3,"Hello","here we go again","today"))
        val list= NotesDatabase.getInstance(view.context).notesDao().viewAllNotes()

        notes_recyclerview.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)

        val adapter = NotesAdapter(view.context,list)

        notes_recyclerview.adapter = adapter

        fab.setOnClickListener{
           //childFragmentManager.beginTransaction().replace(R.id.frame_layout,AddNoteFragment()).commit()
            fragmentManager?.beginTransaction()?.replace(R.id.frame_layout,AddNoteFragment())
                ?.addToBackStack(null)
                ?.commit()

        }
        adapter.setOnLayoutClickListener(this)

    }

    override fun OnNoteClicked(note: Note, position: Int) {
        Toast.makeText(this.context,note.title,Toast.LENGTH_SHORT).show()

    }


}