package com.genxlead.lingeriePer_FitApp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.genxlead.lingeriePer_FitApp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)


val fontName = GoogleFont("Rubik")

val fontFamily = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider
    )
)


@Composable
fun createShyawayTypography(colors: CustomColors): Typography {

    val scale = getScalingFactor()

    return Typography(
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp * scale,
            lineHeight =15.sp * scale,
            color = colors.secondary
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp * scale,
            lineHeight = 12.sp * scale,
            letterSpacing = 0.sp,
            color = colors.tertiary
        ),
        bodySmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp * scale,
            lineHeight = 12.sp * scale,
            letterSpacing = 0.sp
        )
    )
}


@Composable
fun getScalingFactor(baseScreenWidth: Int = 300): Float {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.toFloat() / baseScreenWidth
}

@Composable
fun getHeight(height: Int): Dp {
    val scale = getScalingFactor(360)
    return (scale*height).dp
}