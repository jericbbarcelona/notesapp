package com.jbarcelona.notesapp.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey
    var id: String,
    var content: String,
    var createdDate: String
)