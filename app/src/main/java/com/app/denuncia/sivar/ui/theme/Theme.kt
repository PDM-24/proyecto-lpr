package com.denuncia.sivar.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = blue80,
    onPrimary = Color.White,
    secondary = blue50,
    onSecondary = Color.White,
    tertiary = blue100,
    onTertiary = Color.White,
    background = blue100,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = blue80,
    onPrimary = Color.Black,
    secondary = blue50,
    onSecondary = Color.Black,
    tertiary = blue100,
    onTertiary = Color.Black,
    background = Color.White,
    onBackground = Color.Black, //Texts
    surface = Color.White,
    onSurface = Color.Black
)


@Composable
fun DenunciaSivarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            window.navigationBarColor = blue80.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
