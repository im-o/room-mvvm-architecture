package com.stimednp.roommvvm.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    private val id: Int? = null,
    private val title: String? = null,
    private val description: String? = null,
    private val priority: Int? = null
)