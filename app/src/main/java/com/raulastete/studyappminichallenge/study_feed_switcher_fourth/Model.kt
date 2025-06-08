package com.raulastete.studyappminichallenge.study_feed_switcher_fourth

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val quickLessons = listOf(
    QuickLesson(
        "Inside a Cell",
        "A 60‑second tour of organelles",
        "Biology",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFAFD27A),
                Color(0xFF287F3C)
            )
        )
    ),
    QuickLesson(
        "Fractions in a Flash",
        "Understand halves, thirds, and quarters fast",
        "Math",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF1288FF),
                Color(0xFF0B2299)
            )
        )
    ),
    QuickLesson(
        "WWII at a Glance",
        "Key events and dates distilled into one minute",
        "History",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFF5C020),
                Color(0xFFCF5B08)
            )
        )
    ),
    QuickLesson(
        "Bonjour Basics",
        "Greet and introduce yourself in French",
        "Language",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF41A5CC),
                Color(0xFF055270)
            )
        )
    ),
    QuickLesson(
        "Sketch Like Picasso",
        "Quick warm‑up for abstract line art",
        "Art",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFF9D89),
                Color(0xFFD03515)
            )
        )
    ),
    QuickLesson(
        "Binary in 60 Seconds",
        "Count to 32 on one hand using binary",
        "Computer Science",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF41DDBE),
                Color(0xFF087578)
            )
        )
    ),
    QuickLesson(
        "Chord Progression 101",
        "The I‑V‑vi‑IV pattern explained",
        "Music",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF9E77AC),
                Color(0xFF451C52)
            )
        )
    ),
    QuickLesson(
        "Mountains & Valleys",
        "How tectonic plates shape landscapes",
        "Geography",
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF986241),
                Color(0xFF402302)
            )
        )
    )
)

data class QuickLesson(
    val title: String,
    val description: String,
    val subject: String,
    val gradient: Brush
)
