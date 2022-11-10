package de.maddin.tastetestviewer.controller

import de.maddin.tastetestviewer.ext.getAverageScores
import de.maddin.tastetestviewer.ext.getRightGuessesPercent
import de.maddin.tastetestviewer.repository.GuessRepository
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

        return ModelAndView("stats")
    }
}