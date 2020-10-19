package com.stimednp.roommvvm.data.repository

import androidx.lifecycle.LiveData
import com.stimednp.roommvvm.data.db.NoteDatabase
import com.stimednp.roommvvm.data.db.entity.Note

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NoteRepository(
    private val noteDatabase: NoteDatabase
) {

    suspend fun insertNote(note: Note) = noteDatabase.getNoteDao().insertNote(note)

    suspend fun updateNote(note: Note) = noteDatabase.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note) = noteDatabase.getNoteDao().deleteNote(note)

    suspend fun deleteNoteById(id: Int) = noteDatabase.getNoteDao().deleteNoteById(id)

    suspend fun clearNote() = noteDatabase.getNoteDao().clearNote()

    fun getAllNotes(): LiveData<List<Note>> = noteDatabase.getNoteDao().getAllNotes()
}