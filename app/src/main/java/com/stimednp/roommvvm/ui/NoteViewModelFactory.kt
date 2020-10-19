package com.stimednp.roommvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stimednp.roommvvm.data.repository.NoteRepository
import com.stimednp.roommvvm.utils.UtilFunctions.loge

/**
 * Created by rivaldy on Oct/18/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NoteViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(NoteRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            loge(e.message.toString())
        }
        return super.create(modelClass)
    }
}