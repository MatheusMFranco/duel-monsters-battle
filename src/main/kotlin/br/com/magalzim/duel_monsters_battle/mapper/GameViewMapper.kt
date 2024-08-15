package br.com.magalzim.duel_monsters_battle.mapper

import br.com.magalzim.duel_monsters_battle.entity.Duelist
import br.com.magalzim.duel_monsters_battle.view.DuelistView
import org.springframework.stereotype.Component

@Component
class GameViewMapper: Mapper<Duelist, DuelistView> {
    override fun map(t: Duelist): DuelistView {
        return DuelistView(
            id = t.id,
            name = t.name,
            email = t.email,
            avatar = t.avatar
        )
    }
}