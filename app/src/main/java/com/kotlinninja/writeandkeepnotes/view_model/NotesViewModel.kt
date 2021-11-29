package com.kotlinninja.writeandkeepnotes.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kotlinninja.writeandkeepnotes.database.NotesDatabase
import com.kotlinninja.writeandkeepnotes.model.Notes
import com.kotlinninja.writeandkeepnotes.repository.NotesRepository
import kotlinx.coroutines.InternalCoroutinesApi

//androidviewmodel runs in whole application

@InternalCoroutinesApi
class NotesViewModel(application: Application):AndroidViewModel(application) {

    val repository:NotesRepository

    init {  // runs it first
         val dao= NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository= NotesRepository(dao)

    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> = repository.getallNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

     fun updateNotes(notes: Notes) {
         repository.updateNotes(notes)
     }

}