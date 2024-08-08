package br.com.magalzim.duel_monsters_battle.service

import br.com.magalzim.duel_monsters_battle.entity.Duelist
import br.com.magalzim.duel_monsters_battle.exception.NotFoundException
import br.com.magalzim.duel_monsters_battle.form.NewDuelistForm
import br.com.magalzim.duel_monsters_battle.form.UpdateDuelistForm
import br.com.magalzim.duel_monsters_battle.mapper.DuelistFormMapper
import br.com.magalzim.duel_monsters_battle.mapper.DuelistViewMapper
import br.com.magalzim.duel_monsters_battle.repository.DuelistRepository
import br.com.magalzim.duel_monsters_battle.view.DuelistView
import org.springframework.stereotype.Service

@Service
class DuelistService(
    private val repository: DuelistRepository,
    private val duelistViewMapper: DuelistViewMapper,
    private val duelistFormMapper: DuelistFormMapper,
    private val userRoleService: UserRoleService,
) {
    fun findById(id: String?): Duelist {
        if (!id.isNullOrBlank()) {
            return repository.getReferenceById(id)
        }
        throw NotFoundException(Duelist::class)
    }

    fun find(id: String): DuelistView {
        return duelistViewMapper.map(findById(id))
    }

    fun add(dto: NewDuelistForm): DuelistView {
        val duelist = duelistFormMapper.map(dto)
        val result = repository.save(duelist)
        if (result.id != null) {
            userRoleService.save(result.id)
        }
        return duelistViewMapper.map(duelist)
    }

    fun update(dto: UpdateDuelistForm): DuelistView {
        if (dto.id != null) {
            val model = repository.findById(dto.id)
                .orElseThrow{ NotFoundException(Duelist::class) }
            model.name = dto.name.toString()
            model.password = dto.password.toString()
            model.email = dto.email.toString()
            model.avatar = dto.avatar.toString()
            repository.save(model)
            return duelistViewMapper.map(model)
        }
        throw NotFoundException(Duelist::class)
    }

    fun delete(id: String) {
        repository.deleteById(id)
    }
}