package com.stimednp.roommvvm.di

import android.content.Context
import androidx.room.Room
import com.stimednp.roommvvm.data.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by rivaldy on 07,February,2021
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, NoteDatabase.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase) = noteDatabase.getNoteDao()
}