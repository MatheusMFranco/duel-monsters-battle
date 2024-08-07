package br.com.magalzim.duel_monsters_battle.service

import br.com.magalzim.duel_monsters_battle.entity.Duelist
import br.com.magalzim.duel_monsters_battle.exception.NotFoundException
import br.com.magalzim.duel_monsters_battle.model.UserDetail
import br.com.magalzim.duel_monsters_battle.repository.DuelistRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: DuelistRepository,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val duelist = repository.findByEmail(username)?: throw NotFoundException(Duelist::class)
        return UserDetail(duelist)
    }
}