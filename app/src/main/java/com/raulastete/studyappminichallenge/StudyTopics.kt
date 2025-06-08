package com.raulastete.studyappminichallenge

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

val categoryColorMap = mapOf(
    "Biology" to chipCategoryColors[0],
    "Environmental Science" to chipCategoryColors[1],
    "History" to chipCategoryColors[2],
    "Social Studies" to chipCategoryColors[3],
    "Math" to chipCategoryColors[4],
    "Literature" to chipCategoryColors[0],
    "Language Arts" to chipCategoryColors[1],
    "Geography" to chipCategoryColors[2],
    "French" to chipCategoryColors[3],
    "Health" to chipCategoryColors[4],
    "Archaeology" to chipCategoryColors[0],
    "Earth Science" to chipCategoryColors[1],
    "Writing" to chipCategoryColors[2],
    "Civics" to chipCategoryColors[3],
    "Ecology" to chipCategoryColors[4],
    "Economics" to chipCategoryColors[0],
    "English" to chipCategoryColors[1],
    "Language" to chipCategoryColors[2],
    "Spanish" to chipCategoryColors[3],
    "Computer Science" to chipCategoryColors[4],
    "Technology" to chipCategoryColors[0],
    "Grammar" to chipCategoryColors[1],
    "Astronomy" to chipCategoryColors[2],
    "Science" to chipCategoryColors[3]
)

data class StudyTopic(
    val topic: String,
    val categories: List<String>
)