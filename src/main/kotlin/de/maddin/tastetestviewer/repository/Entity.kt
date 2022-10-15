package de.maddin.tastetestviewer.repository

import de.maddin.tastetestviewer.ext.getCorrectPercent
import de.maddin.tastetestviewer.ext.getMaxScored
import de.maddin.tastetestviewer.ext.getMinScored
import de.maddin.tastetestviewer.ext.getScores
import javax.persistence.*

@Entity
@Table(name = "taste_tester")
data class TasteTester(
    val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) {
    @OneToMany(mappedBy = "tasteTester")
    lateinit var rounds: Set<Round>
}

@Entity
@Table(name = "taste_test")
data class TasteTest(
    val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) {
    @OneToMany(mappedBy = "tasteTest")
    lateinit var rounds: Set<Round>

    @OneToMany(mappedBy = "tasteTest")
    lateinit var hatePredictions: Set<HatePrediction>

    @OneToMany(mappedBy = "tasteTest")
    lateinit var favouritePredictions: Set<FavouritePrediction>

    private fun getHateByTasteTester(tasteTester: TasteTester) = hatePredictions
        .first { it.tasteTester.id == tasteTester.id }

    private fun getFavByTasteTester(tasteTester: TasteTester) = favouritePredictions
        .first { it.tasteTester.id == tasteTester.id }

    @SuppressWarnings("WeakerAccess")
    fun getRoundsByTasteTester() = rounds.groupBy { it.tasteTester }

    fun getTasteObjectScoresByAllRounds() = rounds.getScores()

    fun getTasteObjectScoresByTasteTester() = getRoundsByTasteTester()
        .mapValues { it.value.getScores() }

    fun getResultByTasteTester() = getRoundsByTasteTester()
        .mapValues { entry ->
            TasteTestResult(
                minScored = entry.value.getMinScored(),
                maxScored = entry.value.getMaxScored(),
                correctPercent = entry.value.getCorrectPercent(),
                hatePredictionName = getHateByTasteTester(entry.key).tasteObject.name,
                favouritePredictionName = getFavByTasteTester(entry.key).tasteObject.name,
            )
        }
}

@Entity
@Table(name = "round")
data class Round(
    val number: Int,
    @ManyToOne
    val tasteTest: TasteTest,
    @ManyToOne
    val tasteTester: TasteTester,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) : Comparable<Round> {

    @OneToMany(mappedBy = "round")
    lateinit var guesses: Set<Guess>

    override fun compareTo(other: Round) = compareValuesBy(this, other) { it.number }
}

@Entity
@Table(name = "taste_object")
data class TasteObject(
    val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) {
    @OneToMany(mappedBy = "tasteObjectTasted")
    lateinit var guesses: Set<Guess>
}

@Entity
@Table(name = "guess")
data class Guess(
    @ManyToOne
    val tasteObjectTasted: TasteObject,
    @ManyToOne
    val tasteObjectGuessed: TasteObject,
    @ManyToOne
    val round: Round,
    val orderInRound: Int,
    val points: Int?,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) : Comparable<Guess> {
    override fun compareTo(other: Guess) = compareValuesBy(this, other) { it.orderInRound }
}

@Entity
@Table(name = "hate_prediction")
data class HatePrediction(
    @ManyToOne
    val tasteTester: TasteTester,
    @ManyToOne
    val tasteTest: TasteTest,
    @ManyToOne
    val tasteObject: TasteObject,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)

@Entity
@Table(name = "favourite_prediction")
data class FavouritePrediction(
    @ManyToOne
    val tasteTester: TasteTester,
    @ManyToOne
    val tasteTest: TasteTest,
    @ManyToOne
    val tasteObject: TasteObject,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)