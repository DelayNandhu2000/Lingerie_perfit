package com.gxl.lingerieperfit.ui.module.home.domain

import com.gxl.lingerieperfit.ui.base.network.ApiResult
import com.gxl.lingerieperfit.ui.module.home.data.SizeChartContent
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getSizeChart(category: String): Flow<ApiResult<SizeChartContent>>
}