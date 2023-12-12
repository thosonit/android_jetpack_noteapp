import it.thoson.note.database.NoteDao
import it.thoson.note.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun getNote(id: Long): Note? {
        return withContext(Dispatchers.IO) {
            noteDao.getNode(id)
        }
    }

    suspend fun insertNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

    suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(note)
        }
    }

    suspend fun getAllNotes(): List<Note> {
        return withContext(Dispatchers.IO) {
            noteDao.getAllNotes()
        }
    }

    suspend fun deleteNote(note: Note) {
        return withContext(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }
}
