package com.genxlead.lingeriePer_FitApp.ui.base.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.genxlead.lingeriePer_FitApp.ui.base.core.AppUpdate
import com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion.viewModel.HomeViewModel
import com.genxlead.lingeriePer_FitApp.ui.module.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<HomeViewModel> { HomeViewModel() }
    viewModel<SplashViewModel> { SplashViewModel(get()) }
    single { AppUpdate(androidContext()) }

}