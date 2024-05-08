package com.jbarcelona.notesapp.screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbarcelona.notesapp.constants.Constants
import com.jbarcelona.notesapp.database.model.Note
import com.jbarcelona.notesapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val note = _notes as LiveData<List<Note>>

    init {
        getAllNotes()
    }

    fun updateNote(note: Note, newNote: String) {
        viewModelScope.launch {
            mainRepository.updateNote(Note(note.id, newNote, note.createdDate))
            getAllNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            mainRepository.deleteNote(note)
            getAllNotes()
        }
    }

    fun insertNote(note: String) {
        viewModelScope.launch {
            mainRepository.insertNote(
                Note(
                    id = UUID.randomUUID().toString(),
                    content = note,
                    createdDate = SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH).format(Date())
                )
            )
            getAllNotes()
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            val notes = mainRepository.getAllNote()
            _notes.value = notes
        }
    }
}