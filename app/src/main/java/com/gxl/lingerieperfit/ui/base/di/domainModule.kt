package com.gxl.lingerieperfit.ui.base.di

import com.gxl.lingerieperfit.ui.module.home.data.HomeUseCase
import com.gxl.lingerieperfit.ui.module.home.domain.HomeRepository
import com.gxl.lingerieperfit.ui.module.home.domain.HomeRepositoryImpl
import org.koin.dsl.module

val domainModule  = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single { HomeUseCase(get()) }

}