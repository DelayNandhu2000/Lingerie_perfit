package com.gxl.lingerieperfit.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFF566A),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFFCFC),
    onPrimaryContainer = Color(0xFFFFF5F6),

    secondary = Color(0xFF333333),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFF0F2),
    onSecondaryContainer = Color(0xFFFFB2BC),

    tertiary = Color(0xFF7E7E7E),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFDBDBDB),
    onTertiaryContainer = Color(0xFF333333),

    background = Color(0xFFF7F7F7),
    onBackground = Color(0xFF333333),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF333333),

    error = Color(0xFFFF566A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFBBC3),
    onErrorContainer = Color(0xFF333333)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF566A),
    onPrimary = Color(0xFF1B1B1B),
    primaryContainer = Color(0xFF2b2b2b),
    onPrimaryContainer = Color(0xFFFFFFFF),

    secondary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF1B1B1B),
    secondaryContainer = Color(0xFF2b2b2b),
    onSecondaryContainer = Color(0xFFFFFFFF),

    tertiary = Color(0xFFB3B3B3),
    onTertiary = Color(0xFF1B1B1B),
    tertiaryContainer = Color(0xFF333333),
    onTertiaryContainer = Color(0xFFB3B3B3),

    background = Color(0xFF1B1B1B),
    onBackground = Color(0xFFFFFFFF),

    surface = Color(0xFF333333),
    onSurface = Color(0xFFFFFFFF),

    error = Color(0xFFFF566A),
    onError = Color(0xFF1B1B1B),
    errorContainer = Color(0xFF333333),
    onErrorContainer = Color(0xFFFFBBC3)
)

// Custom Color Definitions
data class CustomColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val bgColor1: Color,
    val primaryContainer : Color,
    val primaryStroke : Color,
)

val LightCustomColors = CustomColors(
    primary = Color(0xFFFF566A),
    secondary = Color(0xFF333333),
    tertiary = Color(0xFF7E7E7E),
    bgColor1 = Color(0xFFF7F7F7),
    primaryContainer = Color(0xFFFFFCFC),
    primaryStroke = Color(0xFFFFF0F2)
)

val DarkCustomColors = CustomColors(
    primary = Color(0xFFFF566A),
    secondary = Color(0xFFFFFFFF),
    tertiary = Color(0xFFB3B3B3),
    bgColor1 = Color(0xFF2b2b2b),
    primaryContainer = Color(0xFF2b2b2b),
    primaryStroke = Color(0xFF2b2b2b),
)
val LocalCustomColors = staticCompositionLocalOf { LightCustomColors }



@Composable
fun LingeriePerFitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val customColors = if (darkTheme) DarkCustomColors else LightCustomColors
    val typography = createShyawayTypography(customColors)

    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    val primaryColor = Color(0xFFFF566A)



    SideEffect {
        systemUiController.setStatusBarColor(
            color = primaryColor,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = primaryColor
        )
    }

    CompositionLocalProvider(LocalCustomColors provides customColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}