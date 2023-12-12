package it.thoson.note

import android.app.Application
import it.thoson.note.database.DatabaseProvider

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseProvider.init(applicationContext)
    }
}
