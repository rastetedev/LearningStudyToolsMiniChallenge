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

data class StudyTopic(
    val topic: String,
    val categories: List<String>
)

data class CategoryColor(
    val mainColor: Color,
    val backgroundColor: Color
)

val studyTopics = listOf(
    StudyTopic("Photosynthesis", listOf("Biology", "Environmental Science")),
    StudyTopic("World War II Timeline", listOf("History", "Social Studies")),
    StudyTopic("Introduction to Fractions", listOf("Math")),
    StudyTopic("Elements of a Story", listOf("Literature", "Language Arts")),
    StudyTopic("The Water Cycle", listOf("Geography", "Environmental Science")),
    StudyTopic("Basic French Greetings", listOf("Language", "French")),
    StudyTopic("The Human Skeleton", listOf("Biology", "Health")),
    StudyTopic("Ancient Egyptian Civilizations", listOf("History", "Archaeology")),
    StudyTopic("Solving for X (Algebra Basics)", listOf("Math")),
    StudyTopic("Types of Clouds", listOf("Geography", "Earth Science")),
    StudyTopic("Writing a Thesis Statement", listOf("Writing", "Language Arts")),
    StudyTopic("The Constitution Explained", listOf("Civics", "History")),
    StudyTopic("Food Chains and Webs", listOf("Biology", "Ecology")),
    StudyTopic("Understanding Supply & Demand", listOf("Economics", "Social Studies")),
    StudyTopic("Literary Devices in Poetry", listOf("Literature", "English")),
    StudyTopic("Basic Spanish Verbs", listOf("Language", "Spanish")),
    StudyTopic("Introduction to Coding", listOf("Computer Science", "Technology")),
    StudyTopic("Earthquake Safety Basics", listOf("Geography", "Earth Science")),
    StudyTopic("Subject‑Verb Agreement", listOf("Grammar", "Language Arts")),
    StudyTopic("The Solar System Overview", listOf("Astronomy", "Science"))
)

val categoriesColor = listOf(
    CategoryColor(MainPurple, BackgroundPurple),
    CategoryColor(MainGreen, BackgroundGreen),
    CategoryColor(MainTeal, BackgroundTeal),
    CategoryColor(MainPink, BackgroundPink),
    CategoryColor(MainOrange, BackgroundOrange)
)

val categoryColorMap = mapOf(
    "Biology" to categoriesColor[0],
    "Environmental Science" to categoriesColor[1],
    "History" to categoriesColor[2],
    "Social Studies" to categoriesColor[3],
    "Math" to categoriesColor[4],
    "Literature" to categoriesColor[0],
    "Language Arts" to categoriesColor[1],
    "Geography" to categoriesColor[2],
    "French" to categoriesColor[3],
    "Health" to categoriesColor[4],
    "Archaeology" to categoriesColor[0],
    "Earth Science" to categoriesColor[1],
    "Writing" to categoriesColor[2],
    "Civics" to categoriesColor[3],
    "Ecology" to categoriesColor[4],
    "Economics" to categoriesColor[0],
    "English" to categoriesColor[1],
    "Language" to categoriesColor[2],
    "Spanish" to categoriesColor[3],
    "Computer Science" to categoriesColor[4],
    "Technology" to categoriesColor[0],
    "Grammar" to categoriesColor[1],
    "Astronomy" to categoriesColor[2],
    "Science" to categoriesColor[3]
)

