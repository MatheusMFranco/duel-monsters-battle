package br.com.magalzim.duel_monsters_battle.view

import java.io.Serializable

data class DuelistView(
    val id: String?,
    val name: String,
    val email: String,
    val avatar: String
): Serializable