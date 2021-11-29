package com.kotlinninja.writeandkeepnotes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlinninja.writeandkeepnotes.model.Notes

// for running quaries to access the database
@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Notes>>
// getting all notes from this function
    // this function will return live data list with the type of Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
// if same quaries then replace
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=id")
    fun deleteNotes(id:Int)


    @Update
    fun updateNotes(notes: Notes)

}