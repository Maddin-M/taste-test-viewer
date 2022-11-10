package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.TasteObject
import de.maddin.tastetestviewer.repository.TasteObjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/taste-object")
class TasteObjectController(
    val tasteObjectRepository: TasteObjectRepository,
) {
    @PostMapping("/add")
    fun post(tasteObject: TasteObject): ResponseEntity<Any> {
        tasteObjectRepository.save(tasteObject)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/delete")
    fun delete(id: Int): ResponseEntity<Any> {
        tasteObjectRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }
}