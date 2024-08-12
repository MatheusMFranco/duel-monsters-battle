package br.com.magalzim.duel_monsters_battle.entity

object DuelistTest {
    private fun create(name: String) = Duelist(
        id = "550e8400-e29b-41d4-a716-446655440000",
        name = name,
        email = "john.doe@example.com",
        avatar = "test.png",
        password = "123456"
    )
    fun build() = create("Yugi")
    fun updated() = create("Kaiba")
    fun buildToToken() = create("Pegasus")
}