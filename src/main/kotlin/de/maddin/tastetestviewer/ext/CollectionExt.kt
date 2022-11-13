package de.maddin.tastetestviewer.ext

import de.maddin.tastetestviewer.repository.Guess
import de.maddin.tastetestviewer.repository.Round
import de.maddin.tastetestviewer.repository.TasteObjectScore
import kotlin.math.roundToInt

fun Collection<Round>.getGuesses() = this
    .flatMap { it.guesses }

@JvmName("getAverageScoresRound")
fun Collection<Round>.getAverageScores() = this
    .getGuesses()
    .getAverageScores()

@JvmName("getAverageScoresGuess")
fun Collection<Guess>.getAverageScores() = this
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
    .getAverageScores()
    .let { scores ->
        val minScore = scores.minOf { it.score }
        scores.filter { it.score == minScore }
    }
    .toSet()

fun Collection<Round>.getMaxScored() = this
    .getAverageScores()
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

fun Collection<Guess>.getRightGuessesPercent() = this
    .let { guesses ->
        guesses
            .filter { it.tasteObjectTasted.id == it.tasteObjectGuessed.id }
            .size
            .toDouble()
            .div(guesses.size)
            .times(100)
    }

fun Collection<Guess>.getWrongGuesses() = this
    .filter { it.tasteObjectTasted.name != it.tasteObjectGuessed.name }

fun Collection<Guess>.getRightGuesses() = this
    .filter { it.tasteObjectTasted.name == it.tasteObjectGuessed.name }