package com.raulastete.studyappminichallenge.searchable_study_list_third

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import com.raulastete.studyappminichallenge.ui.theme.StudyAppMiniChallengeTheme

class SearchableStudyListActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyAppMiniChallengeTheme {
                SearchableStudyListScreen()
            }
        }
    }
}