package com.stimednp.roommvvm.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.stimednp.roommvvm.data.db.entity.Note
import com.stimednp.roommvvm.data.repository.NoteRepositoryImpl

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NoteViewModel @ViewModelInject constructor(private val repository: NoteRepositoryImpl) : ViewModel() {

    suspend fun insertNote(note: Note) = repository.insertNote(note)

    suspend fun updateNote(note: Note) = repository.updateNote(note)

    suspend fun deleteNote(note: Note) = repository.deleteNote(note)

    suspend fun deleteNoteById(id: Int) = repository.deleteNoteById(id)

    suspend fun clearNote() = repository.clearNote()

    fun getAllNotes() = repository.getAllNotes()
}