package br.com.magalzim.duel_monsters_battle.service

import br.com.magalzim.duel_monsters_battle.entity.DuelistTest
import br.com.magalzim.duel_monsters_battle.repository.DuelistRepository
import io.mockk.*
import org.junit.jupiter.api.Test

class UserRoleServiceTest {

    val user = DuelistTest.build()

    private val duelistRepository: DuelistRepository = mockk{
        every { findByEmail(any()) } returns user
    }

    private val userService = UserService(duelistRepository)

    @Test
    fun `should find user by email`(){
        userService.loadUserByUsername(user.email)
        verify(exactly = 1) { duelistRepository.findByEmail(user.email) }
    }

}