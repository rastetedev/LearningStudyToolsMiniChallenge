package com.raulastete.studyappminichallenge.scrollable_study_board_fifth

import kotlinx.serialization.Serializable

@Serializable
data class LessonTopic(
    val title: String,
    val category: String
)

val lessonTopics = listOf(
    // Science
    LessonTopic("Photosynthesis Basics", "Science"),
    LessonTopic("Newton’s Laws of Motion", "Science"),
    LessonTopic("The Water Cycle", "Science"),
    LessonTopic("Types of Energy", "Science"),
    LessonTopic("Ecosystems and Biomes", "Science"),
    LessonTopic("Cell Structure and Function", "Science"),
    LessonTopic("States of Matter", "Science"),
    LessonTopic("Gravity and Forces", "Science"),
    LessonTopic("The Human Body Systems", "Science"),
    LessonTopic("Food Chains and Webs", "Science"),

    // Math
    LessonTopic("Solving for X", "Math"),
    LessonTopic("Introduction to Fractions", "Math"),
    LessonTopic("Understanding Decimals", "Math"),
    LessonTopic("Area and Perimeter", "Math"),
    LessonTopic("Mean, Median, and Mode", "Math"),
    LessonTopic("Multiplication Strategies", "Math"),
    LessonTopic("Long Division Basics", "Math"),
    LessonTopic("Intro to Algebraic Expressions", "Math"),
    LessonTopic("Coordinate Grids", "Math"),
    LessonTopic("Percentages and Ratios", "Math"),

    // Language
    LessonTopic("Basic Spanish Greetings", "Language"),
    LessonTopic("Common French Verbs", "Language"),
    LessonTopic("Parts of Speech", "Language"),
    LessonTopic("Verb Tenses Explained", "Language"),
    LessonTopic("Active vs Passive Voice", "Language"),
    LessonTopic("Building Vocabulary", "Language"),
    LessonTopic("Prefixes and Suffixes", "Language"),
    LessonTopic("Capitalization Rules", "Language"),
    LessonTopic("Sentence Types", "Language"),
    LessonTopic("Transition Words", "Language"),

    // History
    LessonTopic("Ancient Egypt", "History"),
    LessonTopic("The Roman Empire", "History"),
    LessonTopic("The Middle Ages", "History"),
    LessonTopic("World War I Overview", "History"),
    LessonTopic("World War II Key Events", "History"),
    LessonTopic("The Cold War", "History"),
    LessonTopic("Civil Rights Movement", "History"),
    LessonTopic("American Revolution", "History"),
    LessonTopic("Industrial Revolution", "History"),
    LessonTopic("Timeline of Key Inventions", "History"),

    // Literature
    LessonTopic("Elements of a Story", "Literature"),
    LessonTopic("Literary Devices", "Literature"),
    LessonTopic("Alliteration and Assonance", "Literature"),
    LessonTopic("Understanding Plot Structure", "Literature"),
    LessonTopic("Theme and Tone", "Literature"),
    LessonTopic("Conflict in Literature", "Literature"),
    LessonTopic("Point of View in Writing", "Literature"),
    LessonTopic("Character Development", "Literature"),
    LessonTopic("Poetry Forms and Structures", "Literature"),
    LessonTopic("Figurative Language", "Literature"),

    // Geography
    LessonTopic("Continents and Oceans", "Geography"),
    LessonTopic("Latitude and Longitude", "Geography"),
    LessonTopic("World Time Zones", "Geography"),
    LessonTopic("Landforms and Bodies of Water", "Geography"),
    LessonTopic("Natural Disasters", "Geography"),
    LessonTopic("Map Reading Skills", "Geography"),
    LessonTopic("Climate Zones", "Geography"),
    LessonTopic("Countries and Capitals", "Geography"),
    LessonTopic("Population Density", "Geography"),
    LessonTopic("Regions of the World", "Geography")
)

val lessonMap: Map<String, List<LessonTopic>> = lessonTopics
    .groupBy { it.category }
    .mapValues { entry ->
        entry.value.sortedBy { it.title }
    }
    .toSortedMap()
