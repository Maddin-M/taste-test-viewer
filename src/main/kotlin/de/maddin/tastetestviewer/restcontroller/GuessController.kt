package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.Guess
import de.maddin.tastetestviewer.repository.GuessRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/rest/guess")
class GuessController(
    val guessRepository: GuessRepository,
) {
    @PostMapping("/add")
    fun post(
        guess: Guess,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        guessRepository.save(guess)
        redirectAttributes.addAttribute(
            "message",
            "Guess added",
        )
        return RedirectView("/success")
    }

    @PostMapping("/delete")
    fun delete(
        id: Int,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        guessRepository.deleteById(id)
        redirectAttributes.addAttribute(
            "message",
            "Guess with ID $id deleted!",
        )
        return RedirectView("/success")
    }
}