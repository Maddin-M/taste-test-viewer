package de.maddin.tastetestviewer.controller

import de.maddin.tastetestviewer.repository.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AdminController(
    val tasteTesterRepository: TasteTesterRepository,
    val tasteObjectRepository: TasteObjectRepository,
    val tasteTestRepository: TasteTestRepository,
    val roundRepository: RoundRepository,
    val guessRepository: GuessRepository,
    val hatePredictionRepository: HatePredictionRepository,
    val favouritePredictionRepository: FavouritePredictionRepository,
) {

    @GetMapping("/admin")
    fun getAdminPage(model: Model): ModelAndView {
        tasteTesterRepository.findAll()
            .also { model.addAttribute("tasteTesters", it) }
            .filter { it.rounds.isEmpty() }
            .let { model.addAttribute("tasteTestersWithNoRounds", it) }
        tasteObjectRepository.findAll()
            .also { model.addAttribute("tasteObjects", it) }
            .filter { it.guesses.isEmpty() }
            .let { model.addAttribute("tasteObjectsWithNoGuesses", it) }
        tasteTestRepository.findAll()
            .also { model.addAttribute("tasteTests", it) }
            .filter { it.rounds.isEmpty() }
            .let { model.addAttribute("tasteTestsWithNoRounds", it) }
        roundRepository.findAll()
            .let { model.addAttribute("rounds", it) }
        guessRepository.findAll()
            .let { model.addAttribute("guesses", it) }
        hatePredictionRepository.findAll()
            .let { model.addAttribute("hatePredictions", it) }
        favouritePredictionRepository.findAll()
            .let { model.addAttribute("favouritePredictions", it) }
        return ModelAndView("admin")
    }
}