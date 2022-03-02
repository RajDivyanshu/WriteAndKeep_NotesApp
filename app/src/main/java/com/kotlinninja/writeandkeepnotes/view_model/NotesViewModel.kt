package com.kotlinninja.writeandkeepnotes.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kotlinninja.writeandkeepnotes.database.NotesDatabase
import com.kotlinninja.writeandkeepnotes.model.Notes
import com.kotlinninja.writeandkeepnotes.repository.NotesRepository
import kotlinx.coroutines.InternalCoroutinesApi

//androidviewmodel runs in whole application
// view model takes data from repository
// viewModel have data in the form of observer, it has observer
// all the activity observe viewModel
// if data is changed in this then activity & fragment update the data according to viewmodel

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

    fun getAllNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

     fun updateNotes(notes: Notes) {
         repository.updateNotes(notes)
     }


    //for filtering
    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> = repository.getMediumNotes()
    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()

}