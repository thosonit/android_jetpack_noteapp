import android.util.Log
import androidx.lifecycle.ViewModel
import it.thoson.note.database.DatabaseProvider
import it.thoson.note.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

class HomeScreenVM: ViewModel() {
    /*PUBLIC PROPERTIES*/
    val notes = MutableStateFlow<List<Note>>(emptyList())

    /*PRIVATE PROPERTIES*/
    private var noteRepository: NoteRepository

    init {
        Log.d("HomeScreenVM", "init")
        val noteDao = DatabaseProvider.provideDatabase().noteDao()
        noteRepository = NoteRepository(noteDao = noteDao)
        getAllNotes()
    }

    fun getAllNotes() {
        runBlocking {
            val newNotes = noteRepository.getAllNotes()
            notes.value = newNotes
        }
    }

    fun deleteNote(note: Note) {
        runBlocking {
            noteRepository.deleteNote(note = note)
        }
    }
}