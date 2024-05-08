package com.jbarcelona.notesapp.repository

import com.jbarcelona.notesapp.database.model.Note
import com.jbarcelona.notesapp.database.dao.NoteDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val noteDao: NoteDao
) : BaseRepository {

    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    override fun getAllNote(): List<Note> {
        return noteDao.getAll()
    }
}