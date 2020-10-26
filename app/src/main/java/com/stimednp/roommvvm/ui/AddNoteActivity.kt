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
import com.stimednp.roommvvm.utils.UtilExtensions.setTextEditable
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var repository: NoteRepository
    private lateinit var factory: NoteViewModelFactory

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        //@todo bad practice because boilerplate code, but we'll be change this later using DI.
        noteDatabase = NoteDatabase(this)
        repository = NoteRepository(noteDatabase)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        note = intent.extras?.getParcelable(MainActivity.NOTE_DATA)

        initToolbar()
        initView()
        initClick()
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        priorityPicker.minValue = 1
        priorityPicker.maxValue = 10

        if (note != null) { //this is for set data to form and update data
            titleET.setTextEditable(note?.title ?: "")
            descriptionET.setTextEditable(note?.description ?: "")
            priorityPicker.value = note?.priority ?: 1
            saveButton.text = getString(R.string.update_note)
        }
    }

    private fun initClick() {
        saveButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val id = if (note != null) note?.id else null
        val title = titleET.text.toString().trim()
        val desc = descriptionET.text.toString().trim()
        val priority = priorityPicker.value

        if (title.isEmpty() || desc.isEmpty()) {
            myToast(getString(R.string.form_empty))
            return
        }

        val note = Note(id = id, title = title, description = desc, priority = priority)
        Coroutines.main {
            if (id != null) { //for update note
                viewModel.updateNote(note).also {
                    myToast(getString(R.string.success_update))
                    finish()
                }
            } else { //for insert note
                viewModel.insertNote(note).also {
                    myToast(getString(R.string.success_save))
                    finish()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}