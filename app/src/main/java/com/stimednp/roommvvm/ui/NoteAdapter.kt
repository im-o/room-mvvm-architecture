package com.stimednp.roommvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stimednp.roommvvm.data.db.entity.Note
import com.stimednp.roommvvm.databinding.ItemNoteBinding

/**
 * Created by rivaldy on Oct/19/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NoteAdapter(private val listener: (Note) -> Unit) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffUtilNote()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item, listener)
    }

    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(note: Note, listener: (Note) -> Unit) {
            binding.apply {
                titleTV.text = note.title
                descriptionTV.text = note.description
                priorityTV.text = note.priority.toString()
            }
            binding.root.setOnClickListener {
                listener(note)
            }
        }
    }

    private class DiffUtilNote : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return newItem == oldItem
        }
    }
}