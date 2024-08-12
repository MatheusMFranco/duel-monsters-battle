package br.com.magalzim.duel_monsters_battle.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_role")
data class UserRole(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "duelist_id", columnDefinition = "CHAR(36)")
    val duelistId: String,

    @Column(name = "role_id")
    val roleId: Byte
)