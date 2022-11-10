package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.TasteTester
import de.maddin.tastetestviewer.repository.TasteTesterRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/taste-tester")
class TasteTesterController(
    val tasteTesterRepository: TasteTesterRepository,
) {
    @PostMapping("/add")
    fun post(tasteTester: TasteTester): ResponseEntity<Any> {
        tasteTesterRepository.save(tasteTester)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/delete")
    fun delete(id: Int): ResponseEntity<Any> {
        tasteTesterRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }
}