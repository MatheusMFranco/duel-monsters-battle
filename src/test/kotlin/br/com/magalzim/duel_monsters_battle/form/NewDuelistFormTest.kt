package br.com.magalzim.duel_monsters_battle.form

object NewDuelistFormTest {
    fun build() = NewDuelistForm(
        name = "Kaiba",
        email = "kaiba@example.com",
        avatar = "blue-eyes-white-dragon",
        password = "123456",
    )
}