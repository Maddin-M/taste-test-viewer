package de.maddin.tastetestviewer.controller

import de.maddin.tastetestviewer.repository.RoundRepository
import de.maddin.tastetestviewer.repository.TasteTestRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController(
    val tasteTestRepository: TasteTestRepository,
    val roundRepository: RoundRepository,
) {

    @GetMapping("/")
    fun getMainPage(
        model: Model,
        @RequestParam(required = false) id: Int?,
    ): ModelAndView {
        id
            ?.let { tasteTestRepository.findById(it).get() }
            ?.let { model.addAttribute("tasteTest", it) }
        id
            ?.let { roundRepository.findRoundByTasteTestId(it) }
            ?.let { model.addAttribute("rounds", it) }
        return ModelAndView("main")
    }
}