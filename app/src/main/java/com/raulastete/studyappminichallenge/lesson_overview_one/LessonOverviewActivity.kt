package com.raulastete.studyappminichallenge.lesson_overview_one

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.platform.LocalConfiguration
import com.raulastete.studyappminichallenge.DeviceMode
import com.raulastete.studyappminichallenge.ui.theme.MainPurple
import com.raulastete.studyappminichallenge.ui.theme.StudyAppMiniChallengeTheme

class LessonOverviewActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(MainPurple.value.toInt())
        )
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            val heightSizeClass = calculateWindowSizeClass(this).heightSizeClass

            val configuration = LocalConfiguration.current

            val deviceMode = when {
                widthSizeClass == WindowWidthSizeClass.Compact &&
                        configuration.orientation == ORIENTATION_PORTRAIT -> {
                    DeviceMode.PhonePortrait
                }

                heightSizeClass == WindowHeightSizeClass.Compact &&
                        configuration.orientation == ORIENTATION_LANDSCAPE -> {
                    DeviceMode.PhoneLandscape
                }

                configuration.orientation == ORIENTATION_PORTRAIT -> {
                    DeviceMode.TabletPortrait
                }

                else -> {
                    DeviceMode.TabletLandscape
                }
            }
            StudyAppMiniChallengeTheme {
                LessonOverviewScreen(deviceMode = deviceMode)
            }
        }
    }
}