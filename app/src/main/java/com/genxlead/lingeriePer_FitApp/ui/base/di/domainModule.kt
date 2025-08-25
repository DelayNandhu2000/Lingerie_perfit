package com.genxlead.lingeriePer_FitApp.ui.base.di

import com.genxlead.lingeriePer_FitApp.ui.module.home.data.HomeUseCase
import com.genxlead.lingeriePer_FitApp.ui.module.home.domain.HomeRepository
import com.genxlead.lingeriePer_FitApp.ui.module.home.domain.HomeRepositoryImpl
import org.koin.dsl.module

val domainModule  = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single { HomeUseCase(get()) }

}