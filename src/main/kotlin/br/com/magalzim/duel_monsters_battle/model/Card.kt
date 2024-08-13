package br.com.magalzim.duel_monsters_battle.model

data class Card(
    val id: Long? = null,
    val name: String,
    val cardType: CardType,
    val description: String? = null,
    val fusedCardNames: List<String> = emptyList(),
    val counters: List<CounterInfo> = emptyList()
)
