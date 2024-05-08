package com.jbarcelona.notesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jbarcelona.notesapp.database.dao.NoteDao
import com.jbarcelona.notesapp.database.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}