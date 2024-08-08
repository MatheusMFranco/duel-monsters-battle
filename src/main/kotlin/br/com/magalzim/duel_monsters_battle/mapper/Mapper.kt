package br.com.magalzim.duel_monsters_battle.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}