package com.example.noteappbylivedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.noteapproom.Room.Note
import com.example.noteapproom.Room.NoteDatabase
import com.example.noteapproom.Room.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel (application: Application): AndroidViewModel(application) {
    private val repository: NoteRepository
    private val notes: LiveData<List<Note>>

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        notes = repository.getNote
    }

    fun getNotes(): LiveData<List<Note>> {
        return notes
    }

    fun addNote(noteText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNote(Note(0, noteText))
        }
    }

    fun editNote(noteID: Int, noteText: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(Note(noteID, noteText))
        }
    }

    fun deleteNote(noteID: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(Note(noteID, ""))
        }
    }
}