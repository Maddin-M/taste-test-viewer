package de.maddin.tastetestviewer.controller

import de.maddin.tastetestviewer.ext.getWrongGuesses
import de.maddin.tastetestviewer.ext.getAverageScores
import de.maddin.tastetestviewer.ext.getRightGuesses
import de.maddin.tastetestviewer.ext.getRightGuessesPercent
import de.maddin.tastetestviewer.repository.GuessRepository
import de.maddin.tastetestviewer.repository.TasteObjectCount
import de.maddin.tastetestviewer.repository.TasteTesterScore
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class StatsController(
    val guessRepository: GuessRepository,
) {
    @GetMapping("/stats")
    fun getStatsPage(model: Model): ModelAndView {
        val guesses = guessRepository.findAll()

        guesses
            .getAverageScores()
            .sortedByDescending { it.score }
            .let { model.addAttribute("tasteObjectAverageScores", it) }

        guesses
            .groupBy { it.round.tasteTester }
            .map { TasteTesterScore(it.key.name, it.value.getRightGuessesPercent(), it.value.size) }
            .sortedWith(compareByDescending<TasteTesterScore> { it.roundsPlayed }.thenByDescending { it.score })
            .let { model.addAttribute("tasteTestersRightGuessesPercent", it) }

        guesses
            .filter { it.points == 10 }
            .groupBy { it.tasteObjectTasted.name }
            .map { TasteObjectCount(it.key, it.value.size) }
            .sortedWith(compareByDescending { it.amount })
            .let { model.addAttribute("bestTasteObjects", it) }

        guesses
            .filter { it.points == 0 }
            .groupBy { it.tasteObjectTasted.name }
            .map { TasteObjectCount(it.key, it.value.size) }
            .sortedWith(compareByDescending { it.amount })
            .let { model.addAttribute("worstTasteObjects", it) }

        guesses
            .groupBy { it.tasteObjectTasted }
            .filter { it.value.size == it.value.getRightGuesses().size }
            .map { it.key.name }
            .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it })
            .let { model.addAttribute("tasteObjectsAlwaysGuessedRight", it) }

        guesses
            .groupBy { it.tasteObjectTasted }
            .filter { it.value.size == it.value.getWrongGuesses().size }
            .map { it.key.name }
            .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it })
            .let { model.addAttribute("tasteObjectsAlwaysGuessedWrong", it) }

        return ModelAndView("stats")
    }
}