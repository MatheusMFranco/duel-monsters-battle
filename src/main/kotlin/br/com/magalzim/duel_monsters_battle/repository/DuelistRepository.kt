package br.com.magalzim.duel_monsters_battle.repository

import br.com.magalzim.duel_monsters_battle.entity.Duelist
import org.springframework.data.jpa.repository.JpaRepository

interface DuelistRepository: JpaRepository<Duelist, String> {
    fun findByEmail(username: String?): Duelist?
}