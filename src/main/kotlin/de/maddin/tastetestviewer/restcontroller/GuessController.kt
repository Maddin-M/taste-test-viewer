package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.Guess
import de.maddin.tastetestviewer.repository.GuessRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/guess")
class GuessController(
    val guessRepository: GuessRepository,
) {

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun post(
        guess: Guess,
    ) : ResponseEntity<Any> {
        guessRepository.save(guess)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping
    fun get() = ResponseEntity(guessRepository.findAll(), HttpStatus.OK)
}