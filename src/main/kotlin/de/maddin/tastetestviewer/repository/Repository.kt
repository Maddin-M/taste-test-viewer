package de.maddin.tastetestviewer.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TasteTesterRepository : JpaRepository<TasteTester, Int>

@Repository
interface TasteTestRepository : JpaRepository<TasteTest, Int> {
    @Transactional
    @Modifying
    @Query("UPDATE TasteTest SET name = ?2 WHERE id = ?1")
    fun updateNameById(id: Int, name: String)
}

@Repository
interface RoundRepository : JpaRepository<Round, Int>

@Repository
interface TasteObjectRepository : JpaRepository<TasteObject, Int> {
    @Transactional
    @Modifying
    @Query("UPDATE TasteObject SET name = ?2 WHERE id = ?1")
    fun updateNameById(id: Int, name: String)
}

@Repository
interface GuessRepository : JpaRepository<Guess, Int> {
    @Transactional
    @Modifying
    @Query("UPDATE Guess SET points = ?2 WHERE id = ?1")
    fun updatePointsById(id: Int, points: Int?)
}

@Repository
interface HatePredictionRepository : JpaRepository<HatePrediction, Int> {
    fun findByTasteTesterAndTasteTest(
        tasteTester: TasteTester,
        tasteTest: TasteTest,
    ): Set<HatePrediction>

    @Transactional
    @Modifying
    @Query("UPDATE HatePrediction SET taste_object_id = ?2 WHERE id = ?1")
    fun updateTasteObjectById(id: Int, tasteObjectId: Int)
}

@Repository
interface FavouritePredictionRepository : JpaRepository<FavouritePrediction, Int> {
    fun findByTasteTesterAndTasteTest(
        tasteTester: TasteTester,
        tasteTest: TasteTest,
    ): Set<FavouritePrediction>

    @Transactional
    @Modifying
    @Query("UPDATE FavouritePrediction SET taste_object_id = ?2 WHERE id = ?1")
    fun updateTasteObjectById(id: Int, tasteObjectId: Int)
}