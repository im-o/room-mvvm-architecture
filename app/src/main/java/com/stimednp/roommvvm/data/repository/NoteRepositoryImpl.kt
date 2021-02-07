package com.stimednp.roommvvm.data.repository

import com.stimednp.roommvvm.data.db.NoteDao
import com.stimednp.roommvvm.data.db.entity.Note
import javax.inject.Inject

/**
 * Created by rivaldy on 07,February,2021
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override fun getAllNotes() = noteDao.getAllNotes()

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    override suspend fun deleteNoteById(id: Int) = noteDao.deleteNoteById(id)

    override suspend fun clearNote() = noteDao.clearNote()
}