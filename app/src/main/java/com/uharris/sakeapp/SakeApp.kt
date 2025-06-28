package com.uharris.sakeapp

import android.app.Application
import com.uharris.sakeapp.di.dataModule
import com.uharris.sakeapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SakeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SakeApp)
            modules(dataModule, viewModelModule)
        }
    }
}