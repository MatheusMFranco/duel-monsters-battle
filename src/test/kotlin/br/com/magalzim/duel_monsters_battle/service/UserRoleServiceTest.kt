package br.com.magalzim.duel_monsters_battle.service

import br.com.magalzim.duel_monsters_battle.entity.UserRole
import br.com.magalzim.duel_monsters_battle.repository.UserRoleRepository
import io.mockk.*
import org.junit.jupiter.api.Test
import java.util.UUID

class UserRoleServiceTest {

    private val userRole = UserRole(1, UUID.randomUUID().toString(), 1)

    private val userRoleRepository: UserRoleRepository = mockk{
        every { save(any()) } returns userRole
        every { getReferenceById(any()) } returns userRole
    }

    private val userRoleService = UserRoleService(userRoleRepository)

    @Test
    fun `should add role`() {
        userRoleService.save(userRole.duelistId)
        verify(exactly = 1) { userRoleRepository.save(any()) }
    }
}