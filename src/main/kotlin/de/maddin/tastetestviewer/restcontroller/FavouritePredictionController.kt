package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.FavouritePrediction
import de.maddin.tastetestviewer.repository.FavouritePredictionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/favourite-prediction")
class FavouritePredictionController(
    val favouritePredictionRepository: FavouritePredictionRepository,
) {
    @PostMapping("/add")
    fun post(favouritePrediction: FavouritePrediction): ResponseEntity<Any> {
        favouritePredictionRepository.findByTasteTesterAndTasteTest(
            favouritePrediction.tasteTester,
            favouritePrediction.tasteTest,
        ).ifEmpty {
            favouritePredictionRepository.save(favouritePrediction)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.badRequest()
            .body("Taste Tester already has FavouritePrediction for this Taste Test!")
    }

    @PostMapping("/delete")
    fun delete(id: Int): ResponseEntity<Any> {
        favouritePredictionRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }
}