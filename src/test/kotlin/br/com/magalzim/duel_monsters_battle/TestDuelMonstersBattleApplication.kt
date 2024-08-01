package br.com.magalzim.duel_monsters_battle

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<DuelMonstersBattleApplication>().with(TestcontainersConfiguration::class).run(*args)
}
