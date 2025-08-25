package com.genxlead.lingeriePer_FitApp.ui.base.navGraph

import kotlinx.serialization.Serializable


sealed class Screen {

    @Serializable
    object Splash : Screen()

    @Serializable
    object Home : Screen()

    @Serializable
    object YesIdo : Screen()

    @Serializable
    object SelfMeasure : Screen()

    @Serializable
    object FinalResult : Screen()
}