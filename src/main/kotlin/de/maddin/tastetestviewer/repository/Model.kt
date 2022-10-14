package de.maddin.tastetestviewer.repository

import java.text.DecimalFormat

data class TasteObjectScore(
    val name: String,
    val score: Double,
) {
    @SuppressWarnings("WeakerAccess")
    fun getScoreString(): String = DecimalFormat("0.##").format(score)

    fun getRoundedScore() = getScoreString().substringBefore(",")
}

fun Set<Round>.getRoundScores() = this
    .flatMap { round -> round.guesses }
    .groupBy { guess -> guess.tasteObjectTasted.name }
    .map { tasteObject ->
        TasteObjectScore(
            tasteObject.key,
            tasteObject.value
                .mapNotNull { guess -> guess.points }
                .average()
        )
    }

fun List<TasteObjectScore>.getMinScored() = let {
    val minScore = this.minOf { it.score }
    this.filter { it.score == minScore }
}

fun List<TasteObjectScore>.getMaxScored() = let {
    val maxScore = this.maxOf { it.score }
    this.filter { it.score == maxScore }
}