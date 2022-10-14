package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.TasteTester
import de.maddin.tastetestviewer.repository.TasteTesterRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/rest/taste-tester")
class TasteTesterController(
    val tasteTesterRepository: TasteTesterRepository,
) {
    @PostMapping("/add")
    fun post(
        tasteTester: TasteTester,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        tasteTesterRepository.save(tasteTester)
        redirectAttributes.addAttribute(
            "message",
            "TasteTester \"${tasteTester.name}\" added!",
        )
        return RedirectView("/success")
    }

    @PostMapping("/delete")
    fun delete(
        id: Int,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        tasteTesterRepository.deleteById(id)
        redirectAttributes.addAttribute(
            "message",
            "TasteTester with ID $id deleted!",
        )
        return RedirectView("/success")
    }
}