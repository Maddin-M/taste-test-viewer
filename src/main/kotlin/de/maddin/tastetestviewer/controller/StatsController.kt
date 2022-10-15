package de.maddin.tastetestviewer.controller

import de.maddin.tastetestviewer.ext.getAverageScores
import de.maddin.tastetestviewer.repository.GuessRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class StatsController(
    val guessRepository: GuessRepository,
) {
    @GetMapping("/stats")
    fun getStatsPage(
        model: Model,
    ): ModelAndView {
        guessRepository.findAll()
            .getAverageScores()
            .sortedByDescending { it.score }
            .let { model.addAttribute("tasteObjectAverageScores", it) }
        return ModelAndView("stats")
    }
}