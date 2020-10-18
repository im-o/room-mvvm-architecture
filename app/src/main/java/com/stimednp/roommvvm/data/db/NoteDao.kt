package com.stimednp.roommvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.stimednp.roommvvm.data.db.entity.Note

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    @Query("DELETE FROM note_table")
    suspend fun clearNote()

    @Query("DELETE FROM note_table WHERE id = :id") //you can use this too, for delete note by id.
    suspend fun deleteById(id: Int)
}