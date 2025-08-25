package com.genxlead.lingeriePer_FitApp.ui.module.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.genxlead.lingeriePer_FitApp.ui.base.core.AppUpdate
import com.genxlead.lingeriePer_FitApp.ui.base.core.AppUpdateType
import com.genxlead.lingeriePer_FitApp.ui.base.core.FireBaseDatabaseRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class SplashViewModel(
    val firebase: FireBaseDatabaseRepository
): ViewModel(), KoinComponent {

    val appUpdate: AppUpdate by inject()

    suspend fun getAppVersionDetails(): AppUpdateType {
        try {
            val appConfig = firebase.getAppUpdateConfig()
            return when {
                appConfig.forceUpdate -> AppUpdateType.IMMEDIATE
                !appConfig.forceUpdate -> AppUpdateType.FLEXIBLE
                else -> AppUpdateType.IMMEDIATE
            }

        } catch (e: Exception) {
            Log.e("Firebase: ", e.message.toString())
        }

        return AppUpdateType.IMMEDIATE
    }
}