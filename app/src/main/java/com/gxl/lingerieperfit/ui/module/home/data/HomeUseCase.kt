package com.gxl.lingerieperfit.ui.module.home.data

import com.gxl.lingerieperfit.ui.base.network.ApiResult
import com.gxl.lingerieperfit.ui.module.home.domain.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeUseCase(val homeRepository: HomeRepository){
    suspend fun getSizeChart(category: String): Flow<ApiResult<SizeChartContent>> {
       return homeRepository.getSizeChart(category)
    }
}