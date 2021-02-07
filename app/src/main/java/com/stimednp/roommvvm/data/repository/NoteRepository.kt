package com.stimednp.roommvvm.data.repository

import androidx.lifecycle.LiveData
import com.stimednp.roommvvm.data.db.entity.Note

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

interface NoteRepository {
    fun getAllNotes(): LiveData<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun deleteNoteById(id: Int)
    suspend fun clearNote()
}