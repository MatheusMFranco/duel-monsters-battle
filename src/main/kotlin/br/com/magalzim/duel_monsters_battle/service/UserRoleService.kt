package br.com.magalzim.duel_monsters_battle.service

import br.com.magalzim.duel_monsters_battle.entity.UserRole
import br.com.magalzim.duel_monsters_battle.model.UserRoleAuthority
import br.com.magalzim.duel_monsters_battle.repository.UserRoleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserRoleService(private val repository: UserRoleRepository) {
    fun save(duelist: UUID) {
        repository.save(UserRole(
            duelistId = duelist,
            roleId = UserRoleAuthority.DUELIST.ordinal.toLong() + 1
        ))
    }
}