package com.kotlinninja.writeandkeepnotes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlinninja.writeandkeepnotes.model.Notes

// for running quaries to access the database
@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>
// getting all notes from this function
    // this function will return live data list with the type of Notes


    // extra queries for filtering
    //getting high notes
    @Query("SELECT * FROM Notes WHERE priority= 3")
    fun getHighNotes(): LiveData<List<Notes>>

    //getting medium notes
    @Query("SELECT * FROM Notes WHERE priority= 2")
    fun getMediumNotes(): LiveData<List<Notes>>

    //getting low notes
    @Query("SELECT * FROM Notes WHERE priority= 1")
    fun getLowNotes(): LiveData<List<Notes>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
// if same quaries then replace
    fun insertNotes(notes: Notes)

//    @Delete
//    fun deleteNotes(notes: Notes)
    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)


    @Update
    fun updateNotes(notes: Notes)

}
// in interface all methods are abstract without using abstract keyword
// in abstract method functions are declared only there is no body is written