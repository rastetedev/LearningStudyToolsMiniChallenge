package com.raulastete.studyappminichallenge.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = MainPurple,
    primaryContainer = BackgroundPurple,
    surface = White,
    background = SecondaryBackground,
    onBackground = Gray,
    onSurface = Black,
    onSurfaceVariant = BlackVariant,
)

@Composable
fun StudyAppMiniChallengeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}