package com.stimednp.roommvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.stimednp.roommvvm.R
import com.stimednp.roommvvm.data.db.NoteDatabase
import com.stimednp.roommvvm.data.db.entity.Note
import com.stimednp.roommvvm.data.repository.NoteRepository
import com.stimednp.roommvvm.utils.Coroutines
import com.stimednp.roommvvm.utils.UtilExtensions.myToast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var repository: NoteRepository
    private lateinit var factory: NoteViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        //@todo bad practice because boilerplate code, but we'll be change this later using DI.
        noteDatabase = NoteDatabase(this)
        repository = NoteRepository(noteDatabase)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        initView()
        initToolbar()
        initClick()
    }

    private fun initView() {
        priorityPicker.minValue = 1
        priorityPicker.maxValue = 10
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initClick() {
        saveButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val title = titleET.text.toString().trim()
        val desc = descriptionET.text.toString().trim()
        val priority = priorityPicker.value

        if (title.isEmpty() || desc.isEmpty()) {
            myToast("Some data is empty, checked your form")
            return
        }

        Coroutines.main {
            val note = Note(id = null, title = title, description = desc, priority = priority)
            viewModel.insertNote(note).also {
                myToast("Success add data")
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}