package br.com.magalzim.duel_monsters_battle.view;

import br.com.magalzim.duel_monsters_battle.entity.DuelistTest

object DuelistViewTest {
    private fun create(name: String) = DuelistView(
            id = DuelistTest.build().id.toString(),
            name = name,
            email = DuelistTest.build().email,
            avatar = DuelistTest.build().avatar
    )
    fun build() = create("Kaiba")
    fun updated() = create("Yugi")
}