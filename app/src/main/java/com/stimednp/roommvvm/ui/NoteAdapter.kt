package com.stimednp.roommvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stimednp.roommvvm.R
import com.stimednp.roommvvm.data.db.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

/**
 * Created by rivaldy on Oct/19/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NoteAdapter(private val listener: (Note) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNotes: List<Note> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItem(listNotes[position], listener)
    }

    override fun getItemCount() = listNotes.size

    fun getNoteAt(position: Int): Note {
        return listNotes[position]
    }

    class NoteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(note: Note, listener: (Note) -> Unit) {
            view.apply {
                titleTV.text = note.title
                descriptionTV.text = note.description
                priorityTV.text = note.priority.toString()
            }
            view.setOnClickListener {
                listener(note)
            }
        }
    }
}