package it.thoson.note.database

import androidx.room.Database
import androidx.room.RoomDatabase
import it.thoson.note.models.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}