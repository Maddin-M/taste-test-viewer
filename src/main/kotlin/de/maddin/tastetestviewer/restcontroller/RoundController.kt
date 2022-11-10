package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.Round
import de.maddin.tastetestviewer.repository.RoundRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/round")
class RoundController(
    val roundRepository: RoundRepository,
) {
    @PostMapping("/add")
    fun post(round: Round): ResponseEntity<Any> {
        roundRepository.save(round)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/delete")
    fun delete(id: Int): ResponseEntity<Any> {
        roundRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }
}