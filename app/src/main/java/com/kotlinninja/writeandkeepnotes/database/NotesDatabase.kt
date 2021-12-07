package com.kotlinninja.writeandkeepnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlinninja.writeandkeepnotes.dao.NotesDao
import com.kotlinninja.writeandkeepnotes.model.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// exteding from room databse
@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    // the function which are created in abstract class have no body

    // for accessing noteDao
    abstract fun myNotesDao(): NotesDao


    //companion object like java have static keyword
    companion object {

        @Volatile //easily accessible
// final instance for checking and adding data
        var INSTANCE: NotesDatabase? = null

        @InternalCoroutinesApi
        fun getDatabaseInstance(context: Context): NotesDatabase {

            val tempInstance = INSTANCE

            // condition to check roomdatabse instance created or not
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                // if it is null then run synchronized block to build database

                val roomDatabseInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes")
                        .allowMainThreadQueries().build()
                INSTANCE = roomDatabseInstance //reference
                return roomDatabseInstance
            }
            //allowMainThreadQueries() allow to work queries on main thread

        }

    }
}