package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.HatePrediction
import de.maddin.tastetestviewer.repository.HatePredictionRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/hate-prediction")
class HatePredictionController(
    val hatePredictionRepository: HatePredictionRepository,
) {

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun post(
        hatePrediction: HatePrediction,
    ) : ResponseEntity<Any> {
        hatePredictionRepository.save(hatePrediction)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping
    fun get() = ResponseEntity(hatePredictionRepository.findAll(), HttpStatus.OK)
}