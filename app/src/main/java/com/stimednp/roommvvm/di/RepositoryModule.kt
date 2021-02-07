package com.stimednp.roommvvm.di

import com.stimednp.roommvvm.data.db.NoteDao
import com.stimednp.roommvvm.data.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by rivaldy on 28,January,2021
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesNoteRepository(noteDao: NoteDao) = NoteRepositoryImpl(noteDao)
}