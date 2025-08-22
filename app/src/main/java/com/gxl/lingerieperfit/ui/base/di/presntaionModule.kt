package com.gxl.lingerieperfit.ui.base.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.gxl.lingerieperfit.ui.module.home.presentaion.viewModel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get()) }
}