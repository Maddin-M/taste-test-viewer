package de.maddin.tastetestviewer.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TasteTesterRepository : JpaRepository<TasteTester, Int>

@Repository
interface TasteTestRepository : JpaRepository<TasteTest, Int>

@Repository
interface RoundRepository : JpaRepository<Round, Int>

@Repository
interface TasteObjectRepository : JpaRepository<TasteObject, Int>

@Repository
interface GuessRepository : JpaRepository<Guess, Int>

@Repository
interface HatePredictionRepository : JpaRepository<HatePrediction, Int> {
    fun findByTasteTesterAndTasteTest(
        tasteTester: TasteTester,
        tasteTest: TasteTest,
    ): Set<HatePrediction>
}

@Repository
interface FavouritePredictionRepository : JpaRepository<FavouritePrediction, Int> {
    fun findByTasteTesterAndTasteTest(
        tasteTester: TasteTester,
        tasteTest: TasteTest,
    ): Set<FavouritePrediction>
}