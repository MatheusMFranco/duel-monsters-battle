package br.com.magalzim.duel_monsters_battle.repository

import br.com.magalzim.duel_monsters_battle.entity.Game
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository: JpaRepository<Game, Long>