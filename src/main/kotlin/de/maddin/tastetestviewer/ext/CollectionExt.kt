package de.maddin.tastetestviewer.ext

import de.maddin.tastetestviewer.repository.Round
import de.maddin.tastetestviewer.repository.TasteObjectScore
import kotlin.math.roundToInt

fun Collection<Round>.getGuesses() = this
    .flatMap { it.guesses }

fun Collection<Round>.getScores() = this
    .getGuesses()
    .groupBy { guess -> guess.tasteObjectTasted.name }
    .map { tasteObject ->
        TasteObjectScore(
            tasteObject.key,
            tasteObject.value
                .mapNotNull { guess -> guess.points }
                .average()
        )
    }
    .toSet()

fun Collection<Round>.getMinScored() = this
    .getScores()
    .let { scores ->
        val minScore = scores.minOf { it.score }
        scores.filter { it.score == minScore }
    }
    .toSet()

fun Collection<Round>.getMaxScored() = this
    .getScores()
    .let { scores ->
        val maxScore = scores.maxOf { it.score }
        scores.filter { it.score == maxScore }
    }
    .toSet()

fun Collection<Round>.getCorrectPercent() = this
    .getGuesses()
    .let { guesses ->
        "${
            (guesses
                .filter { it.tasteObjectTasted.id == it.tasteObjectGuessed.id }
                .size
                .toDouble() / guesses.size * 100)
                .roundToInt()
        } %"
    }