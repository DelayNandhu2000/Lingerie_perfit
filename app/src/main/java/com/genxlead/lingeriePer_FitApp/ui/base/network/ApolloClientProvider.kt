package com.genxlead.lingeriePer_FitApp.ui.base.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class ApolloClientProvider(
    private val serverUrl: String = "https://mcstaging2.shyaway.com/graphql/"
) {

    fun getClient(): ApolloClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return ApolloClient.Builder()
            .serverUrl(serverUrl)
            .okHttpClient(okHttpClient) // attach OkHttp client
            .build()
    }
}