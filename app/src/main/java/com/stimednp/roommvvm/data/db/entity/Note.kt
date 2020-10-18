package com.stimednp.roommvvm.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String?,
    val description: String?,
    val priority: Int?
)