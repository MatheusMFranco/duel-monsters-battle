package br.com.magalzim.duel_monsters_battle.repository

import br.com.magalzim.duel_monsters_battle.entity.UserRole
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository: JpaRepository<UserRole, Long>