package it.thoson.note.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import it.thoson.note.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getNode(id: Long): Note?

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table")
    suspend fun getAllNotes(): List<Note>
}
