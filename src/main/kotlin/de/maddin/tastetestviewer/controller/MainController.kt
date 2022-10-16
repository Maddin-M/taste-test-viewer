package de.maddin.tastetestviewer.controller

import de.maddin.tastetestviewer.repository.TasteTestRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController(
    val tasteTestRepository: TasteTestRepository,
) {

    @GetMapping("/")
    fun getMainPage(
        model: Model,
        @RequestParam(required = false) id: Int?,
    ): ModelAndView {
        tasteTestRepository.findAll()
            .let { model.addAttribute("tasteTests", it) }
        id
            ?.also { model.addAttribute("openTasteTestId", it) }
            ?.let { tasteTestRepository.findById(it).get() }
            ?.let { model.addAttribute("tasteTest", it) }
        return ModelAndView("main")
    }
}