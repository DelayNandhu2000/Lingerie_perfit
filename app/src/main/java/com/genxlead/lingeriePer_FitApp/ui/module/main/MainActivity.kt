package com.genxlead.lingeriePer_FitApp.ui.module.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.facebook.applinks.AppLinkData
import com.google.android.play.core.install.model.AppUpdateType
import com.genxlead.lingeriePer_FitApp.ui.base.core.AppUpdate.Companion.UPDATE_REQUEST_CODE
import com.genxlead.lingeriePer_FitApp.ui.base.navGraph.SetUpNavigation
import com.genxlead.lingeriePer_FitApp.ui.module.splash.SplashViewModel
import com.genxlead.lingeriePer_FitApp.ui.theme.LingeriePerFitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            LingeriePerFitTheme {
                val splashViewModel = koinViewModel<SplashViewModel>()
                val scope = rememberCoroutineScope()

                UpdateApp(scope, splashViewModel)
                HandleFacebookDeferredDeepLink(this)

                Scaffold { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = Color(0xFFF7F7F7)
                    ) {
                        SetUpNavigation()
                    }
                }
            }
        }
    }

    @Composable
    private fun UpdateApp(
        scope: CoroutineScope, viewModel: SplashViewModel, onDismiss: () -> Unit = {}
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current

        // Used to listen for installation state for Immediate update
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.appUpdate.checkInstallationIsPending(this@MainActivity)
            }
        }

        DisposableEffect(Unit) {
            viewModel.appUpdate.checkUpdate(onDismiss) { result ->
                scope.launch {
                    val updateType = viewModel.getAppVersionDetails()
                    when (updateType) {
                        com.genxlead.lingeriePer_FitApp.ui.base.core.AppUpdateType.IMMEDIATE -> {
                            startUpdate(
                                result, this@MainActivity
                            )

                            lifecycleOwner.lifecycle.addObserver(observer)
                        }

                        com.genxlead.lingeriePer_FitApp.ui.base.core.AppUpdateType.FLEXIBLE -> {
                            startUpdate(
                                result, this@MainActivity, AppUpdateType.FLEXIBLE
                            )
                            viewModel.appUpdate.appUpdateManager.registerListener(viewModel.appUpdate.listenUpdateProgress({
                                Toast.makeText(applicationContext, "Downloading.....", Toast.LENGTH_SHORT).show()
                            }) {
                                Toast.makeText(applicationContext, "Downloaded", Toast.LENGTH_SHORT).show()
                                appUpdateManager.completeUpdate()
                            })
                        }

                        else -> {}
                    }
                }
            }

            onDispose {
                viewModel.appUpdate.appUpdateManager.unregisterListener(viewModel.appUpdate.listenUpdateProgress())
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private val updateLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.data == null) return@registerForActivityResult
        if (result.resultCode == UPDATE_REQUEST_CODE) {
            Toast.makeText(applicationContext, "App update: Update Success", Toast.LENGTH_SHORT).show()
        }
    }

    @Composable
    private fun HandleFacebookDeferredDeepLink(context: Context) {

        if (!FacebookSdk.isInitialized()) return

        FacebookSdk.setAutoInitEnabled(true)
        FacebookSdk.fullyInitialize()

        AppLinkData.fetchDeferredAppLinkData(context) { appLinkData ->
            if (appLinkData != null) {
                Toast.makeText(applicationContext, "FacebookDeepLink: ${appLinkData.targetUri}", Toast.LENGTH_SHORT).show()
                Log.e("FacebookDeepLink: ", appLinkData.targetUri.toString())

            } else {
                Log.e("FacebookDeepLink: ", "No deferred App Link data found")
            }
        }
    }

}