package de.maddin.tastetestviewer.repository

import javax.persistence.*
import kotlin.math.roundToInt

@Entity
@Table(name = "taste_tester")
data class TasteTester(
    val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) {
    @OneToMany(
        mappedBy = "tasteTester",
        cascade = [CascadeType.ALL],
    )
    lateinit var rounds: Set<Round>

    @OneToMany(
        mappedBy = "tasteTester",
        cascade = [CascadeType.ALL],
    )
    lateinit var hatePredictions: Set<HatePrediction>

    @OneToMany(
        mappedBy = "tasteTester",
        cascade = [CascadeType.ALL],
    )
    lateinit var favouritePredictions: Set<FavouritePrediction>

    @SuppressWarnings("WeakerAccess")
    fun getRoundScores() = rounds.getRoundScores()

    fun getMaxScored() = getRoundScores()
        .let { roundScores ->
            val maxScore = roundScores.maxOf { it.score }
            roundScores.filter { it.score == maxScore }
        }

    fun getMinScored() = getRoundScores()
        .let { roundScores ->
            val minScore = roundScores.minOf { it.score }
            roundScores.filter { it.score == minScore }
        }

    fun getCorrectPercent() = rounds
        .flatMap { it.guesses }
        .let { guesses ->
            "${
                (guesses
                    .filter { it.tasteObjectTasted.id == it.tasteObjectGuessed.id }
                    .size
                    .toDouble() / guesses.size * 100)
                    .roundToInt()
            } %"
        }
}

@Entity
@Table(name = "taste_test")
data class TasteTest(
    val name: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
) {
//    @ManyToMany
//    @JoinTable(
//        name = "taste_test_taste_testers",
//        joinColumns = [JoinColumn(name = "taste_test_id")],
//        inverseJoinColumns = [JoinColumn(name = "taste_tester_id")],
//    )
//    lateinit var tasteTesters: Set<TasteTester>

    @OneToMany(
        mappedBy = "tasteTest",
        cascade = [CascadeType.ALL],
    )
    lateinit var rounds: Set<Round>

    fun getTasteTestersFoo() = rounds.groupBy { it.tasteTester }

    fun getRoundScores() = rounds
        .getRoundScores()

    fun getRoundScoresFoo() = rounds
        .groupBy { it.tasteTester }
        .mapValues { it.value.toSet().getRoundScores() }
        .let {

        }

    // tasteTestRepository.findById(1).get().getTasteTestersFoo().values.first().flatMap { it.guesses }.groupBy { it.tasteObjectTasted.name }.map {tasteObject->TasteObjectScore(tasteObject.key, tasteObject.value.mapNotNull { it.points }.average())}
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
    @OneToMany(
        mappedBy = "round",
        cascade = [CascadeType.ALL],
    )
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
    @OneToMany(
        mappedBy = "tasteObjectTasted",
        cascade = [CascadeType.ALL],
    )
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