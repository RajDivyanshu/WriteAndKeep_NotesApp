package com.kotlinninja.writeandkeepnotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// this data class makes table
@Parcelize
@Entity(tableName="Notes")
class Notes (

    //automatically generate the primary key
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,

    var title: String,

    var subTitle:String,

    var notes:String,

    var date:String,

    var priority:String

    ):Parcelable