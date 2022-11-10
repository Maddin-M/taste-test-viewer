package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.Guess
import de.maddin.tastetestviewer.repository.GuessRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/guess")
class GuessController(
    val guessRepository: GuessRepository,
) {
    @PostMapping("/add")
    fun post(guess: Guess): ResponseEntity<Any> {
        guessRepository.save(guess)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/delete")
    fun delete(id: Int): ResponseEntity<Any> {
        guessRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }
}