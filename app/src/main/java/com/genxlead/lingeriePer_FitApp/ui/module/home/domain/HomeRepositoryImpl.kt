package com.genxlead.lingeriePer_FitApp.ui.module.home.domain

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.genxlead.lingeriePer_FitApp.GetCalculatorContentQuery
import com.genxlead.lingeriePer_FitApp.ui.base.network.ApiResult
import com.genxlead.lingeriePer_FitApp.ui.base.network.SafeApiCall
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.SizeChartContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HomeRepositoryImpl(
    val apolloClient: ApolloClient
) : HomeRepository, SafeApiCall {

    override suspend fun getSizeChart(category: String): Flow<ApiResult<SizeChartContent>> = flow {
        emit(ApiResult.Loading)
        val response =
            apolloClient.query(
                GetCalculatorContentQuery(category)
            ).execute()
        Log.d("TAGss!!", "getSizeChartResponse: $response")
        val content = response.data?.getCalculatorContent
        if (response.hasErrors()) {
            emit(ApiResult.Error(false , response.exception.hashCode(), response.exception?.message))
        } else {
            if (content != null) {
                val mapped = SizeChartContent(
                    staticBlock = content.staticblock,
                    sizeImage = content.sizeimage,
                )
                emit(ApiResult.Success(mapped))
            }
        }
    }

}