package com.stimednp.roommvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stimednp.roommvvm.data.db.entity.Note

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)

abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        const val DB_NAME = "note_database.db"
    }
}