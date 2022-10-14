package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.Round
import de.maddin.tastetestviewer.repository.RoundRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/rest/round")
class RoundController(
    val roundRepository: RoundRepository,
) {
    @PostMapping("/add")
    fun post(
        round: Round,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        roundRepository.save(round)
        redirectAttributes.addAttribute(
            "message",
            "Round ${round.number} for TasteTester ${round.tasteTester.name} " +
                    "in TasteTest ${round.tasteTest.name} added!",
        )
        return RedirectView("/success")
    }
}