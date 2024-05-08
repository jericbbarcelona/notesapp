package com.jbarcelona.notesapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbarcelona.notesapp.screen.viewmodel.NoteViewModel

@Composable
@ExperimentalMaterialApi
fun NoteListPage(viewModel: NoteViewModel) {

    val noteList by viewModel.note.observeAsState()
    var noteText by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notes"
                    )
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(value = noteText, onValueChange = {
                        noteText = it
                    }, singleLine = true)
                    Button(onClick = {
                        viewModel.insertNote(noteText)
                        noteText = ""
                    }) {
                        Text(text = "Add")
                    }
                }

                noteList?.let {
                    LazyColumn(content = {
                        itemsIndexed(it) { _, item ->
                            NoteItem(note = item, onDelete = {
                                viewModel.deleteNote(note = item)
                            }, onEdit = {
                                noteText = item.content
                                viewModel.updateNote(item, noteText)
                            })
                        }
                    })
                } ?: Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "No data found",
                    fontSize = 18.sp
                )
            }
        }
    )
}