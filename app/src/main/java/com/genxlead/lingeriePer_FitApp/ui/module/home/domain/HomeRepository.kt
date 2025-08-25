package com.genxlead.lingeriePer_FitApp.ui.module.home.domain

import com.genxlead.lingeriePer_FitApp.ui.base.network.ApiResult
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.SizeChartContent
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getSizeChart(category: String): Flow<ApiResult<SizeChartContent>>
}