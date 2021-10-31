package com.example.noteapproom.Room

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val getNote: LiveData<List<Note>> = noteDao.retrieveNote()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
}