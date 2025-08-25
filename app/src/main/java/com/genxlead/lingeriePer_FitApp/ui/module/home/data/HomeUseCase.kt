package com.genxlead.lingeriePer_FitApp.ui.module.home.data

import com.genxlead.lingeriePer_FitApp.ui.base.network.ApiResult
import com.genxlead.lingeriePer_FitApp.ui.module.home.domain.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeUseCase(val homeRepository: HomeRepository){
    suspend fun getSizeChart(category: String): Flow<ApiResult<SizeChartContent>> {
        return homeRepository.getSizeChart(category)
    }
}
