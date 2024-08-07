package br.com.magalzim.duel_monsters_battle.view

import org.springframework.http.HttpStatus

object ErrorViewTest {
    fun create(name: String) = ErrorView(
        status = HttpStatus.NOT_FOUND.value(),
        error = HttpStatus.NOT_FOUND.name,
        message = "Duelist ID not found!",
        path = "repository/DuelistRepository"
    )
}