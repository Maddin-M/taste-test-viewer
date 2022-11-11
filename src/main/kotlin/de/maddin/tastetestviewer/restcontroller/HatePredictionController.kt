package de.maddin.tastetestviewer.restcontroller

import de.maddin.tastetestviewer.repository.HatePrediction
import de.maddin.tastetestviewer.repository.HatePredictionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/hate-prediction")
class HatePredictionController(
    val hatePredictionRepository: HatePredictionRepository,
) {
    @PostMapping("/add")
    fun post(hatePrediction: HatePrediction): ResponseEntity<Any> {
        hatePredictionRepository.findByTasteTesterAndTasteTest(
            hatePrediction.tasteTester,
            hatePrediction.tasteTest,
        ).ifEmpty {
            hatePredictionRepository.save(hatePrediction)
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.badRequest()
            .body("Taste Tester already has HatePrediction for this Taste Test!")
    }

    @PostMapping("/delete")
    fun delete(id: Int): ResponseEntity<Any> {
        hatePredictionRepository.deleteById(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/edit")
    fun edit(id: Int, tasteObjectId: Int): ResponseEntity<Any> {
        hatePredictionRepository.updateTasteObjectById(id, tasteObjectId)
        return ResponseEntity.ok().build()
    }
}