package com.stimednp.roommvvm.ui

import androidx.lifecycle.ViewModel
import com.stimednp.roommvvm.data.db.entity.Note
import com.stimednp.roommvvm.data.repository.NoteRepository

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NoteViewModel(
    private val repository: NoteRepository
): ViewModel() {

    suspend fun insertNote(note: Note) = repository.insertNote(note)

    suspend fun updateNote(note: Note) = repository.updateNote(note)

    suspend fun deleteNote(note: Note) = repository.deleteNote(note)

    suspend fun deleteNoteById(id: Int) = repository.deleteNoteById(id)

    suspend fun clearNote() = repository.clearNote()

    fun getAllNotes() = repository.getAllNotes()
}