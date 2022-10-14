package de.maddin.tastetestviewer.controller

import de.maddin.tastetestviewer.repository.TasteObjectRepository
import de.maddin.tastetestviewer.repository.TasteTestRepository
import de.maddin.tastetestviewer.repository.TasteTesterRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AdminController(
    val tasteTesterRepository: TasteTesterRepository,
    val tasteObjectRepository: TasteObjectRepository,
    val tasteTestRepository: TasteTestRepository,
) {

    @GetMapping("/admin")
    fun getAdminPage(
        model: Model,
    ): ModelAndView {
        tasteTesterRepository.findAll()
            .also { model.addAttribute("tasteTesters", it) }
            .filter { it.rounds.isEmpty() }
            .let { model.addAttribute("tasteTestersWithNoRounds", it) }
        tasteObjectRepository.findAll()
            .filter { it.guesses.isEmpty() }
            .let { model.addAttribute("tasteObjectsWithNoGuesses", it) }
        tasteTestRepository.findAll()
            .let { model.addAttribute("tasteTests", it) }
        return ModelAndView("admin")
    }
}