package br.com.magalzim.duel_monsters_battle.form

object UpdateDuelistFormTest {
    fun build() = UpdateDuelistForm(
        id = "550e8400-e29b-41d4-a716-446655440000",
        avatar = "obelisk",
        name = "Seto",
        email = "kaibacorp@example.com",
        password = "123456"
    )
    fun empty() = UpdateDuelistForm(
        id = null,
        avatar = null,
        name = null,
        email = null,
        password = null
    )
}