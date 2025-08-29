package dev.fizcode.attendance

import android.app.Application
import dev.fizcode.attendance.di.initKoin
import org.koin.android.ext.koin.androidContext

class AttendanceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AttendanceApp)
        }
    }
}
