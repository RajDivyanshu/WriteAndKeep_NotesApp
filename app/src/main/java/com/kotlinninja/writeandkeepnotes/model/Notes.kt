package com.kotlinninja.writeandkeepnotes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// this data class makes table
@Parcelize
@Entity(tableName="Notes")
class Notes (

    //automatically generate the primary key
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id") var id:Int?=null,

    @SerializedName("Title") var title: String,

    @SerializedName("SubTitle") var subTitle:String,

    @SerializedName("Notes") var notes:String,

    @SerializedName("Date") var date:String,

    @SerializedName("Priority") var priority:String

    ):Parcelable