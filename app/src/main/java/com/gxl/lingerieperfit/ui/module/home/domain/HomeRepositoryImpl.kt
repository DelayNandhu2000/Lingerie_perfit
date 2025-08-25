package com.gxl.lingerieperfit.ui.module.home.domain

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.gxl.lingerieperfit.GetCalculatorContentQuery
import com.gxl.lingerieperfit.ui.base.network.ApiResult
import com.gxl.lingerieperfit.ui.base.network.SafeApiCall
import com.gxl.lingerieperfit.ui.module.home.data.SizeChartContent
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