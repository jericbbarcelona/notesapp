package com.jbarcelona.notesapp.repository

import com.jbarcelona.notesapp.database.model.Note

interface BaseRepository {
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getAllNote(): List<Note>
}