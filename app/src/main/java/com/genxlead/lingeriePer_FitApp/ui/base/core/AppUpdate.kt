package com.genxlead.lingeriePer_FitApp.ui.base.core

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

class AppUpdate(val context: Context) {

    companion object {
        const val UPDATE_REQUEST_CODE = 1001
    }

    val appUpdateManager: AppUpdateManager = AppUpdateManagerFactory.create(context)

    fun checkUpdate(noUpdate: () -> Unit, onSuccess: AppUpdate.(AppUpdateInfo) -> Unit) {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                onSuccess(appUpdateInfo)
            } else {
                noUpdate()
                Log.e("App update" ,"No Update Available")
            }
        }.addOnFailureListener {
            noUpdate()
            Log.e("App update: ", "Error: ${it.message}")
        }
    }

    fun startUpdate(appUpdateInfo: AppUpdateInfo, activity: Activity, updateType: Int = AppUpdateType.IMMEDIATE) {
        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            activity,
            AppUpdateOptions.defaultOptions(updateType),
            UPDATE_REQUEST_CODE
        )
    }

    // If app type is Immediate
    fun checkInstallationIsPending(activity: Activity) {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                startUpdate(appUpdateInfo, activity)
            }
        }
    }

    // Only used for flexible update
    fun listenUpdateProgress(onDownloading: () -> Unit = {}, onDownloadComplete: () -> Unit = {}) =
        InstallStateUpdatedListener { state ->
            when (state.installStatus()) {
                InstallStatus.DOWNLOADED -> onDownloadComplete()
                InstallStatus.DOWNLOADING -> onDownloading()
                else -> Log.e("App update:", "${state.installStatus()}")
            }
        }

}

enum class AppUpdateType {
    LATEST, IMMEDIATE, FLEXIBLE, NONE
}

fun Context.getAppVersion(): String {
    return packageManager.getPackageInfo(packageName, 0).versionName.orEmpty()
}