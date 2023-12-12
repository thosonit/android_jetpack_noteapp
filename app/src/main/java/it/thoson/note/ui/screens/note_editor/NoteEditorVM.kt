package it.thoson.note.ui.screens.note_editor

import NoteRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import it.thoson.note.database.DatabaseProvider
import it.thoson.note.models.Note
import kotlinx.coroutines.runBlocking
import java.util.Date

class NoteEditorVM : ViewModel() {
    /*PUBLIC PROPERTIES*/

    var title by mutableStateOf("")
    var content by mutableStateOf("")

    /*PRIVATE PROPERTIES*/
    private val noteRepository: NoteRepository

    init {
        val noteDao = DatabaseProvider.provideDatabase().noteDao()
        noteRepository = NoteRepository(noteDao = noteDao)
    }

    fun setPreview(title: String, content: String) {
        this.title = title
        this.content = content
    }

    fun getNote(id: Long) {
        runBlocking {
            val note = noteRepository.getNote(id)
            if (note != null) {
                title = note.title
                content = note.content
            }
        }
    }

    fun insertNote() {
        val note = Note(
            title = title,
            content = content,
            createdAt = Utils.dateToString(Date()),
            updatedAt = Utils.dateToString(Date())
        )
        runBlocking {
            noteRepository.insertNote(note)
        }
    }

    fun updateNote(noteId: Long) {
        val newNote = Note(
            id = noteId,
            title = title,
            content = content,
            createdAt = Utils.dateToString(Date()),
            updatedAt = Utils.dateToString(Date())
        )
        runBlocking {
            noteRepository.updateNote(newNote)
        }
    }
}