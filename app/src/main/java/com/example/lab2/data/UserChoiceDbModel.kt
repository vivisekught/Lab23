package com.example.lab2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_choices")
data class UserChoiceDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var flags: String,
    var figure: String
)