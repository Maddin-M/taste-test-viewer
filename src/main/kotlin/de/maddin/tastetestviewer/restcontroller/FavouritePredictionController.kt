package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.FavouritePrediction
import de.maddin.tastetestviewer.repository.FavouritePredictionRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/rest/Favourite-prediction")
class FavouritePredictionController(
    val favouritePredictionRepository: FavouritePredictionRepository,
) {
    @PostMapping("/add")
    fun post(
        favouritePrediction: FavouritePrediction,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        favouritePredictionRepository.save(favouritePrediction)
        redirectAttributes.addAttribute(
            "message",
            "FavouritePrediction added!",
        )
        return RedirectView("/success")
    }

    @PostMapping("/delete")
    fun delete(
        id: Int,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        favouritePredictionRepository.deleteById(id)
        redirectAttributes.addAttribute(
            "message",
            "FavouritePrediction with ID $id deleted!",
        )
        return RedirectView("/success")
    }
}