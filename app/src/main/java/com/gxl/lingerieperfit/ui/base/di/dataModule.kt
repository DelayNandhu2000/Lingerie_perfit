package com.gxl.lingerieperfit.ui.base.di

import com.gxl.lingerieperfit.ui.base.network.ApolloClientProvider
import com.gxl.lingerieperfit.ui.base.network.RemoteDataSource
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single { ApolloClientProvider().getClient() }
    single { RemoteDataSource() }
    single <HttpClient>{ RemoteDataSource.create() }
}