package com.stimednp.roommvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.stimednp.roommvvm.R
import com.stimednp.roommvvm.data.db.NoteDatabase
import com.stimednp.roommvvm.data.db.entity.Note
import com.stimednp.roommvvm.data.repository.NoteRepository
import com.stimednp.roommvvm.databinding.ActivityAddNoteBinding
import com.stimednp.roommvvm.utils.Coroutines
import com.stimednp.roommvvm.utils.UtilExtensions.myToast
import com.stimednp.roommvvm.utils.UtilExtensions.setTextEditable

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding;

    private lateinit var viewModel: NoteViewModel
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var repository: NoteRepository
    private lateinit var factory: NoteViewModelFactory

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.priorityPicker.minValue = 1
        binding.priorityPicker.maxValue = 10

        if (note != null) { //this is for set data to form and update data
            binding.titleET.setTextEditable(note?.title ?: "")
            binding.descriptionET.setTextEditable(note?.description ?: "")
            binding.priorityPicker.value = note?.priority ?: 1
            binding.saveButton.text = getString(R.string.update_note)
        }
    }

    private fun initClick() {
        binding.saveButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val id = if (note != null) note?.id else null
        val title = binding.titleET.text.toString().trim()
        val desc = binding.descriptionET.text.toString().trim()
        val priority = binding.priorityPicker.value

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