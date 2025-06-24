package com.raulastete.studyappminichallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raulastete.studyappminichallenge.lesson_overview_one.LessonOverviewActivity
import com.raulastete.studyappminichallenge.scrollable_study_board_fifth.ScrollableStudyBoardActivity
import com.raulastete.studyappminichallenge.searchable_study_list_third.SearchableStudyListActivity
import com.raulastete.studyappminichallenge.study_feed_switcher_fourth.StudyFeedSwitcherActivity
import com.raulastete.studyappminichallenge.ui.theme.StudyAppMiniChallengeTheme
import com.raulastete.studyappminichallenge.word_of_the_day_second.WordOfTheDayActivity
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            StudyAppMiniChallengeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                LessonOverviewActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "Mini challenge #1 - Lesson Overview",
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                WordOfTheDayActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "Mini challenge #2 - Word of the day",
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                SearchableStudyListActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "Mini challenge #3 - Searchable Study List",
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                StudyFeedSwitcherActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "Mini challenge #4 - Study Feed Switcher",
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                ScrollableStudyBoardActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "Mini challenge #5 - Scrollable Study Board",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

sealed interface DeviceMode {
    object PhonePortrait : DeviceMode
    object PhoneLandscape : DeviceMode
    object TabletPortrait : DeviceMode
    object TabletLandscape : DeviceMode
}
