package com.stimednp.roommvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<ArrayList<Note>>
}