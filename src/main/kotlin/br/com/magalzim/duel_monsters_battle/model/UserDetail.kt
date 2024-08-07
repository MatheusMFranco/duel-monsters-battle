package br.com.magalzim.duel_monsters_battle.model

import br.com.magalzim.duel_monsters_battle.entity.Duelist
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val duelist: Duelist
): UserDetails {
    override fun getAuthorities() = duelist.role
    override fun getPassword() = duelist.password
    override fun getUsername() = duelist.email
}