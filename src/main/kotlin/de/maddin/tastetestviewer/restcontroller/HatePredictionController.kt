package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.HatePrediction
import de.maddin.tastetestviewer.repository.HatePredictionRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/rest/hate-prediction")
class HatePredictionController(
    val hatePredictionRepository: HatePredictionRepository,
) {
    @PostMapping("/add")
    fun post(
        hatePrediction: HatePrediction,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        hatePredictionRepository.save(hatePrediction)
        redirectAttributes.addAttribute(
            "message",
            "HatePrediction added!",
        )
        return RedirectView("/success")
    }

    @PostMapping("/delete")
    fun delete(
        id: Int,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        hatePredictionRepository.deleteById(id)
        redirectAttributes.addAttribute(
            "message",
            "HatePrediction with ID $id deleted!",
        )
        return RedirectView("/success")
    }
}