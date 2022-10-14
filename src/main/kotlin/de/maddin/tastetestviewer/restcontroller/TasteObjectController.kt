package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.TasteObject
import de.maddin.tastetestviewer.repository.TasteObjectRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/rest/taste-object")
class TasteObjectController(
    val tasteObjectRepository: TasteObjectRepository,
) {
    @PostMapping("/add")
    fun post(
        tasteObject: TasteObject,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        tasteObjectRepository.save(tasteObject)
        redirectAttributes.addAttribute(
            "message",
            "TasteObject \"${tasteObject.name}\" added!",
        )
        return RedirectView("/success")
    }

    @PostMapping("/delete")
    fun delete(
        id: Int,
        redirectAttributes: RedirectAttributes,
    ): RedirectView {
        tasteObjectRepository.deleteById(id)
        redirectAttributes.addAttribute(
            "message",
            "TasteObject with ID $id deleted!",
        )
        return RedirectView("/success")
    }
}