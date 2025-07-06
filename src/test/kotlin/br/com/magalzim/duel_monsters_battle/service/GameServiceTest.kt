package br.com.magalzim.duel_monsters_battle.service

import br.com.magalzim.duel_monsters_battle.entity.Duelist
import br.com.magalzim.duel_monsters_battle.entity.DuelistTest
import br.com.magalzim.duel_monsters_battle.exception.NotFoundException
import br.com.magalzim.duel_monsters_battle.form.NewDuelistFormTest
import br.com.magalzim.duel_monsters_battle.form.UpdateDuelistFormTest
import br.com.magalzim.duel_monsters_battle.mapper.DuelistFormMapper
import br.com.magalzim.duel_monsters_battle.mapper.DuelistViewMapper
import br.com.magalzim.duel_monsters_battle.repository.DuelistRepository
import br.com.magalzim.duel_monsters_battle.view.DuelistViewTest
import io.mockk.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class GameServiceTest {

    private val updatedDuelist = DuelistTest.updated()
    private val newDuelist = DuelistTest.build()

    private val duelistRepository: DuelistRepository = mockk{
        every { findByEmail(any()) } returns newDuelist
        every { getReferenceById(any()) } returns newDuelist
        every { findById(any()) } returns Optional.of(updatedDuelist)
        every { save(any()) } returns updatedDuelist
        every { deleteById(any()) } just Runs
    }

    private val duelistViewMapper: DuelistViewMapper = mockk{
        every { map(any()) } returns DuelistViewTest.build()
    }

    private val duelistFormMapper: DuelistFormMapper = mockk{
        every { map(any()) } returns newDuelist
    }

    private val userRoleService: UserRoleService = mockk{
        every { save(any()) } just Runs
    }

    private val duelistService = DuelistService(
        duelistRepository,
        duelistViewMapper,
        duelistFormMapper,
        userRoleService
    )

    @Test
    fun `should find an duelist`(){
        duelistService.findById(newDuelist.id.toString())
        verify(exactly = 1) { duelistRepository.findById(any()) }
    }

    @Test
    fun `should not find an duelist`(){
        val current = assertThrows<NotFoundException> {
            duelistService.findById(null)
        }
        Assertions.assertThat(current.message).isEqualTo("Duelist ID not found!")
    }

    @Test
    fun `should duelist name be Kaiba`() {
        val slot = slot<Duelist>()
        every { duelistViewMapper.map(capture(slot)) } returns DuelistViewTest.build()
        duelistService.find(updatedDuelist.id.toString())
        Assertions.assertThat(slot.captured.name).isEqualTo(updatedDuelist.name)
        Assertions.assertThat(slot.captured.email).isEqualTo(updatedDuelist.email)
        Assertions.assertThat(slot.captured.avatar).isEqualTo(updatedDuelist.avatar)
    }

    @Test
    fun `should add duelist`() {
        every { duelistViewMapper.map(any()) } returns DuelistViewTest.build()
        val newAuthorView = duelistService.add(NewDuelistFormTest.build())
        Assertions.assertThat(newAuthorView.name).isEqualTo("Kaiba")
    }

    @Test
    fun `should update duelist`() {
        every { duelistViewMapper.map(any()) } returns DuelistViewTest.updated()
        val updatedAuthorView = duelistService.update(UpdateDuelistFormTest.build())
        Assertions.assertThat(updatedAuthorView.name).isEqualTo("Yugi")
    }

    @Test
    fun `should throw not found exception when duelist is not found`() {
        every { duelistRepository.findById(any()) } returns Optional.empty()
        val current = assertThrows<NotFoundException> {
            duelistService.update(UpdateDuelistFormTest.empty())
        }
        Assertions.assertThat(current.message).isEqualTo("Duelist ID not found!")
    }

    @Test
    fun `should delete a duelist`() {
        duelistService.delete(newDuelist.id.toString())
        verify(exactly = 1) { duelistRepository.deleteById(any()) }
    }
}