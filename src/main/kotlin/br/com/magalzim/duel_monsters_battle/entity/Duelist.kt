package br.com.magalzim.duel_monsters_battle.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDate
import java.util.UUID

@Entity
data class Duelist(
    @Id
    @UuidGenerator
    val id: UUID? = null,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(name = "register_date")
    val registerDate: LocalDate = LocalDate.now(),
    var name: String,
    var avatar: String,
    var password: String?,
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "duelist_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var role: List<Role> = mutableListOf(),
)