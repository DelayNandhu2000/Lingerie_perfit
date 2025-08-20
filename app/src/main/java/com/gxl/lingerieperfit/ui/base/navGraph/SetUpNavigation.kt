package com.gxl.lingerieperfit.ui.base.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gxl.lingerieperfit.ui.module.home.presentaion.HomeScreen
import com.gxl.lingerieperfit.ui.module.splash.SplashScreen


@Composable
fun SetUpNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash) {
        composable<Screen.Splash> {
            SplashScreen(navController)
        }

        composable<Screen.Splash> {
            HomeScreen(navController)
        }
    }
}