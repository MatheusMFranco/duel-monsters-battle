package br.com.magalzim.duel_monsters_battle.mapper

import br.com.magalzim.duel_monsters_battle.entity.Duelist
import br.com.magalzim.duel_monsters_battle.form.NewDuelistForm
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DuelistFormMapper(private val passwordEncoder: PasswordEncoder): Mapper<NewDuelistForm, Duelist> {
    override fun map(t: NewDuelistForm): Duelist {
        return Duelist(
            name = t.name.toString(),
            email = t.email.toString(),
            avatar = t.avatar.toString(),
            password = passwordEncoder.encode(t.password.toString())
        )
    }
}