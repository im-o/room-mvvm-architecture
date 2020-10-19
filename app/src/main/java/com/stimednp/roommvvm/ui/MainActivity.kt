package com.stimednp.roommvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.roommvvm.R
import com.stimednp.roommvvm.data.db.NoteDatabase
import com.stimednp.roommvvm.data.repository.NoteRepository
import com.stimednp.roommvvm.utils.Coroutines
import com.stimednp.roommvvm.utils.UtilExtensions.openActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var repository: NoteRepository
    private lateinit var factory: NoteViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //@todo bad practice because boilerplate code, but we'll be change this later using DI.
        noteDatabase = NoteDatabase(this)
        repository = NoteRepository(noteDatabase)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        initClick()
        showData()
    }

    private fun initClick() {
        addNoteFAB.setOnClickListener {
            openActivity(AddNoteActivity::class.java)
        }
    }

    private fun showData() {
        Coroutines.main {
            viewModel.getAllNotes().observe(this@MainActivity, {
                notesRV.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    setHasFixedSize(true)
                    adapter = NoteAdapter(it)
                }
            })
        }
    }
}