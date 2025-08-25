package com.genxlead.lingeriePer_FitApp.ui.base.di

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp: Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@MainApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}