package it.thoson.note.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var database: AppDatabase? = null
    fun init(context: Context) {
        if (database == null) {
            synchronized(AppDatabase::class.java) {
                database = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "app_database"
                ).allowMainThreadQueries().build()
            }
        }
    }

    fun provideDatabase(): AppDatabase {
        return database!!
    }
}
