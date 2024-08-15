package br.com.magalzim.duel_monsters_battle.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    @NotNull
    val player1: String,

    @Column(nullable = false)
    @NotNull
    val player2: String,

    @Column(nullable = false)
    @NotNull
    var winner: String,

    @Column(nullable = false)
    @NotNull
    val gameDate: LocalDateTime,

    @Column(nullable = false)
    @NotNull
    var player1LifePoints: Int,

    @Column(nullable = false)
    @NotNull
    var player2LifePoints: Int,

    @Column(nullable = false)
    @NotNull
    var rounds: Int,

    @Lob
    @Column(nullable = false)
    var usedCardNames: String,

    @Lob
    @Column(nullable = false)
    var graveyardCardNames: String,

    @Lob
    @Column(nullable = false)
    var bannedCardNames: String,

    @Column(nullable = true)
    var endTime: LocalDateTime? = null
)