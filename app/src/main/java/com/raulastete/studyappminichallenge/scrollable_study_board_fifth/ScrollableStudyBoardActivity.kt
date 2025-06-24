package com.raulastete.studyappminichallenge.scrollable_study_board_fifth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import com.raulastete.studyappminichallenge.ui.theme.StudyAppMiniChallengeTheme

class ScrollableStudyBoardActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyAppMiniChallengeTheme {
                ScrollableStudyGraph()
            }
        }
    }
}