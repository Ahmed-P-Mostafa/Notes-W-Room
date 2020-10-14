package com.example.noteswroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter( context: Context, list: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteHolder>() {
   var list:List<Note>
    val context:Context

    init {
        this.list = list
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)

        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = Note(position,holder.title.toString(),holder.description.toString(),holder.description.toString())
        holder.title.text = list.get(position).title
        holder.description.text = list.get(position).description
        holder.itemView.setOnClickListener{
            onLayoutClicked.OnNoteClicked(note,position)
        }
    }

    override fun getItemCount(): Int {
            return list.size
    }
    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title = itemView.findViewById<TextView>(R.id.note_title)
            val description = itemView.findViewById<TextView>(R.id.note_description)

    }

    interface OnNoteClickListener{
        fun OnNoteClicked(note:Note,position:Int)
    }


        lateinit var onLayoutClicked: OnNoteClickListener

    fun setOnLayoutClickListener(onLayoutClicked:OnNoteClickListener){
        this.onLayoutClicked = onLayoutClicked

    }

}