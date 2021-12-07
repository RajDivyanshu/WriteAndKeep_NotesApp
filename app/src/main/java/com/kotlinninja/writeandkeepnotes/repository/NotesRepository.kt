package com.kotlinninja.writeandkeepnotes.repository

import androidx.lifecycle.LiveData
import com.kotlinninja.writeandkeepnotes.dao.NotesDao
import com.kotlinninja.writeandkeepnotes.model.Notes

// accessing all the function written in NoteDao
class NotesRepository(val dao: NotesDao) {


    fun getallNotes():LiveData<List<Notes>> = dao.getNotes()
// this will get all notes from NotesDao


    //for filtering
    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}