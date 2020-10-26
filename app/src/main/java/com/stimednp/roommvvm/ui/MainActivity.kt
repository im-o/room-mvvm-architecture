package com.stimednp.roommvvm.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stimednp.roommvvm.R
import com.stimednp.roommvvm.data.db.NoteDatabase
import com.stimednp.roommvvm.data.repository.NoteRepository
import com.stimednp.roommvvm.utils.Coroutines
import com.stimednp.roommvvm.utils.UtilExtensions.myToast
import com.stimednp.roommvvm.utils.UtilExtensions.openActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var repository: NoteRepository
    private lateinit var factory: NoteViewModelFactory
    private val noteAdapter: NoteAdapter by lazy {
        NoteAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //@todo bad practice because boilerplate code, but we'll be change this later using DI.
        noteDatabase = NoteDatabase(this)
        repository = NoteRepository(noteDatabase)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        initView()
        observeNotes()
    }

    private fun initView() {
        addNoteFAB.setOnClickListener {
            openActivity(AddNoteActivity::class.java)
        }

        notesRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }

        ItemTouchHelper(itemTouchHelperCallback()).attachToRecyclerView(notesRV)
    }

    private fun observeNotes() {
        Coroutines.main {
            viewModel.getAllNotes().observe(this@MainActivity, {
                noteAdapter.listNotes = it
                noteAdapter.notifyDataSetChanged()
            })
        }
    }

    private fun itemTouchHelperCallback(): ItemTouchHelper.SimpleCallback {
        return object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Coroutines.main {
                    val note = noteAdapter.getNoteAt(viewHolder.adapterPosition)
                    viewModel.deleteNote(note).also {
                        myToast(getString(R.string.success_delete))
                        noteAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun clearNote() {
        val dialog = AlertDialog.Builder(this, R.style.ThemeOverlay_AppCompat_Dialog)
        dialog.setTitle(getString(R.string.clear_note))
            .setMessage(getString(R.string.sure_clear_note))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                Coroutines.main {
                    viewModel.clearNote().also {
                        myToast(getString(R.string.success_clear))
                    }
                }
            }.setNegativeButton(android.R.string.cancel, null).create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clearNoteItem -> clearNote()
        }
        return super.onOptionsItemSelected(item)
    }
}