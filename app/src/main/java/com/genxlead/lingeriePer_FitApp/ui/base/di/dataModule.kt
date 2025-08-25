package com.genxlead.lingeriePer_FitApp.ui.base.di

import com.google.firebase.database.FirebaseDatabase
import com.genxlead.lingeriePer_FitApp.ui.base.core.FireBaseDatabaseRepository
import com.genxlead.lingeriePer_FitApp.ui.base.core.FireBaseDatabaseRepositoryImpl
import com.genxlead.lingeriePer_FitApp.ui.base.network.RemoteDataSource
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single { RemoteDataSource() }
    single <HttpClient>{ RemoteDataSource.create() }
    single<FirebaseDatabase> { FirebaseDatabase.getInstance() }
    single<FireBaseDatabaseRepository> { FireBaseDatabaseRepositoryImpl(get()) }
}