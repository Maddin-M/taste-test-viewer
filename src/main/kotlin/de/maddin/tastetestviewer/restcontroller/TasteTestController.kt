package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.TasteTest
import de.maddin.tastetestviewer.repository.TasteTestRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/rest/taste-test")
class TasteTestController(
    val tasteTestRepository: TasteTestRepository,
) {
    @PostMapping("/add")
    fun post(
        tasteTest: TasteTest,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        tasteTestRepository.save(tasteTest)
        redirectAttributes.addAttribute(
            "message",
            "TasteTest \"${tasteTest.name}\" added!",
        )
        return RedirectView("/success")
    }
}