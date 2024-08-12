package br.com.magalzim.duel_monsters_battle.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Duelist(
    @Id
    @Column(columnDefinition = "CHAR(36)")
    val id: String? = null,

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