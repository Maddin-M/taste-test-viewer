package de.maddin.tastetestviewer.repository

import java.text.DecimalFormat

data class TasteObjectScore(
    val tasteObjectName: String,
    val score: Double,
) {
    @SuppressWarnings("WeakerAccess")
    fun getScoreString(): String = DecimalFormat("0.##")
        .format(score)
        .replace(".", ",")

    fun getRoundedScore() = getScoreString()
        .substringBefore(",")
}

data class TasteTestResult(
    val minScored: Set<TasteObjectScore>,
    val maxScored: Set<TasteObjectScore>,
    val correctPercent: String,
    val hatePredictionName: String,
    val favouritePredictionName: String,
)

data class TasteTesterScore(
    val tasteTesterName: String,
    val score: Double,
    val roundsPlayed: Int,
) {
    fun getScoreString(): String = DecimalFormat("0.#####")
        .format(score)
        .replace(".", ",")
}

// used to count, how often TasteObjects have gotten a score of 10 or 0
data class TasteObjectCount(
    val name: String,
    val amount: Int,
)