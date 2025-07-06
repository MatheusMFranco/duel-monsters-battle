package br.com.magalzim.duel_monsters_battle.service

import br.com.magalzim.duel_monsters_battle.entity.Game
import br.com.magalzim.duel_monsters_battle.exception.NotFoundException
import br.com.magalzim.duel_monsters_battle.model.Coin
import br.com.magalzim.duel_monsters_battle.repository.GameRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class GameService @Autowired constructor(
    private val gameRepository: GameRepository
) {

    private val objectMapper = jacksonObjectMapper()

    fun startGame(
        player1: String,
        player2: String
    ): Game {
        if (player1 == player2) {
            throw IllegalArgumentException("Must be different players")
        }

        val newGame = Game(
            player1 = player1,
            player2 = player2,
            winner = "",
            gameDate = LocalDateTime.now(),
            player1LifePoints = 8000,
            player2LifePoints = 8000,
            rounds = 0,
            usedCardNames = "",
            graveyardCardNames = "",
            bannedCardNames = ""
        )

        return gameRepository.save(newGame)
    }

    fun updateLifePoints(gameId: Long, player: String, points: Int) {
        val game = getGameById(gameId)
        if (player == game.player1) {
            game.player1LifePoints += points
        } else {
            if (player == game.player2) {
                game.player2LifePoints += points
            } else {
                throw NotFoundException(Game::class)
            }
        }
        gameRepository.save(game)
    }

    fun updateMonsterAttack(monster: String, counter: Int): Int {
       return 0
    }

    fun updateMonsterDefense(monster: String, counter: Int): Int {
        return 0
    }

    fun calculateAttackDefenseDifference(monster1: String, monster2: String): Int {
        return 0
    }

    fun endDuel(gameId: Long, winner: String) {
        val game = getGameById(gameId)
        game.endTime = LocalDateTime.now()
        game.winner = winner
        gameRepository.save(game)
    }

    fun flipCoin(): String {
        return if (Random.nextBoolean()) Coin.HEADS.name else Coin.TAILS.name
    }

    fun rollDice(sides: Int): Int {
        return Random.nextInt(1, sides + 1)
    }

    fun advanceRound(gameId: Long) {
        val game = getGameById(gameId)
        game.rounds += 1
        gameRepository.save(game)
    }

    fun getCardDescription(cardName: String): String {
        return "Card Description $cardName"
    }

    fun addUsedCard(gameId: Long, cardName: String) {
        val game = getGameById(gameId)
        val usedCards = objectMapper.readValue<MutableList<String>>(game.usedCardNames)
        usedCards.add(cardName)
        game.usedCardNames = objectMapper.writeValueAsString(usedCards)
        gameRepository.save(game)
    }

    fun addCardToGraveyard(gameId: Long, cardName: String) {
        val game = getGameById(gameId)
        val graveyardCards = objectMapper.readValue<MutableList<String>>(game.graveyardCardNames)
        graveyardCards.add(cardName)
        game.graveyardCardNames = objectMapper.writeValueAsString(graveyardCards)
        gameRepository.save(game)
    }

    fun removeCardFromGraveyard(gameId: Long, cardName: String) {
        val game = getGameById(gameId)
        val graveyardCards = objectMapper.readValue<MutableList<String>>(game.graveyardCardNames)
        graveyardCards.remove(cardName)
        game.graveyardCardNames = objectMapper.writeValueAsString(graveyardCards)
        gameRepository.save(game)
    }

    fun addBannedCard(gameId: Long, cardName: String) {
        val game = getGameById(gameId)
        val bannedCards = objectMapper.readValue<MutableList<String>>(game.bannedCardNames)
        bannedCards.add(cardName)
        game.bannedCardNames = objectMapper.writeValueAsString(bannedCards)
        gameRepository.save(game)
    }

    fun removeBannedCard(gameId: Long, cardName: String) {
        val game = getGameById(gameId)
        val bannedCards = objectMapper.readValue<MutableList<String>>(game.bannedCardNames)
        bannedCards.remove(cardName)
        game.bannedCardNames = objectMapper.writeValueAsString(bannedCards)
        gameRepository.save(game)
    }

    fun getGameById(gameId: Long): Game {
        return gameRepository.findById(gameId).orElseThrow {
            NotFoundException(Game::class)
        }
    }
}
