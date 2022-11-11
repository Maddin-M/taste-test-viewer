package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.TasteTest
import de.maddin.tastetestviewer.repository.TasteTestRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/taste-test")
class TasteTestController(
    val tasteTestRepository: TasteTestRepository,
) {
    @PostMapping("/add")
    fun post(tasteTest: TasteTest): ResponseEntity<Any> {
        tasteTestRepository.save(tasteTest)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/delete")
    fun delete(id: Int): ResponseEntity<Any> {
        tasteTestRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/edit")
    fun edit(id: Int, name: String): ResponseEntity<Any> {
        tasteTestRepository.updateNameById(id, name)
        return ResponseEntity.ok().build()
    }
}