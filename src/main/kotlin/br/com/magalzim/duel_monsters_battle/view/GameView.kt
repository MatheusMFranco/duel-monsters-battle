package br.com.magalzim.duel_monsters_battle.view

import java.io.Serializable
import java.time.LocalDateTime

data class GameView(
    val id: Long,
    val player1: String,
    val player2: String,
    val winner: String,
    val gameDate: LocalDateTime,
    val player1LifePoints: Int,
    val player2LifePoints: Int,
    var rounds: Int,
    val usedCardNames: String,
    val graveyardCardNames: String,
    val bannedCardNames: String,
    val endTime: LocalDateTime
): Serializable