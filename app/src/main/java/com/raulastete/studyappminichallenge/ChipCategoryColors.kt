package com.raulastete.studyappminichallenge

import androidx.compose.ui.graphics.Color
import com.raulastete.studyappminichallenge.ui.theme.BackgroundGreen
import com.raulastete.studyappminichallenge.ui.theme.BackgroundOrange
import com.raulastete.studyappminichallenge.ui.theme.BackgroundPink
import com.raulastete.studyappminichallenge.ui.theme.BackgroundPurple
import com.raulastete.studyappminichallenge.ui.theme.BackgroundTeal
import com.raulastete.studyappminichallenge.ui.theme.MainGreen
import com.raulastete.studyappminichallenge.ui.theme.MainOrange
import com.raulastete.studyappminichallenge.ui.theme.MainPink
import com.raulastete.studyappminichallenge.ui.theme.MainPurple
import com.raulastete.studyappminichallenge.ui.theme.MainTeal

val chipCategoryColors = listOf(
    CategoryColor(MainPurple, BackgroundPurple),
    CategoryColor(MainGreen, BackgroundGreen),
    CategoryColor(MainTeal, BackgroundTeal),
    CategoryColor(MainPink, BackgroundPink),
    CategoryColor(MainOrange, BackgroundOrange)
)

data class CategoryColor(
    val mainColor: Color,
    val backgroundColor: Color
)