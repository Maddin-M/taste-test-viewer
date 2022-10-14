package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.FavouritePrediction
import de.maddin.tastetestviewer.repository.FavouritePredictionRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/Favourite-prediction")
class FavouritePredictionController(
    val favouritePredictionRepository: FavouritePredictionRepository,
) {

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun post(
        favouritePrediction: FavouritePrediction,
    ) : ResponseEntity<Any> {
        favouritePredictionRepository.save(favouritePrediction)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping
    fun get() = ResponseEntity(favouritePredictionRepository.findAll(), HttpStatus.OK)
}