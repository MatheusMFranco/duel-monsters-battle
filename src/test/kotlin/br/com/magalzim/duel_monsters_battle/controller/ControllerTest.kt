package br.com.magalzim.duel_monsters_battle.controller

import br.com.magalzim.duel_monsters_battle.config.DatabaseContainerConfig
import br.com.magalzim.duel_monsters_battle.config.JWTConfig
import br.com.magalzim.duel_monsters_battle.entity.DuelistTest
import br.com.magalzim.duel_monsters_battle.entity.Role
import br.com.magalzim.duel_monsters_battle.model.UserRoleAuthority
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest: DatabaseContainerConfig() {
    @Autowired
    private lateinit var jwtConfig: JWTConfig

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {
        private const val DUELIST_RESOURCE = "/duelists"
    }

    @BeforeEach
    fun setup() {
        token = generateToken(UserRoleAuthority.DUELIST)
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    @Test
    fun `should return code 201 when add duelist`() {
        val newAuthorJson = """
            {
                "name": "John Doe",
                "email": "john.does@example.com",
                "password": "12345678",
                "avatar": "dark-magician"
            }
        """
        mockMvc.perform(MockMvcRequestBuilders.post(DUELIST_RESOURCE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(newAuthorJson))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun `should return code 200 when update author`() {
        val newAuthorJson = """
            {
                "id": "4877c970-4c45-4a3d-be3a-77d2f7846499",
                "name": "John Doe",
                "email": "john.doe@example.com",
                "password": "12345678",
                "avatar": "dark-magician"
            }
        """
        mockMvc.perform(MockMvcRequestBuilders.put(DUELIST_RESOURCE)
            .header("Authorization", "Bearer $token")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newAuthorJson))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun `should return code 200 when login`() {
        val newTopicJson = """
            {
                "username": "john.doe@example.com",
                "password": "123456"
            }
        """
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newTopicJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `should return code 400 when call duelists without token`() {
        mockMvc.delete(DUELIST_RESOURCE.plus("/4877c970-4c45-4a3d-be3a-77d2f7846499")).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `should return code 200 when call duelist with token`() {
        mockMvc.get(DUELIST_RESOURCE.plus("/4877c970-4c45-4a3d-be3a-77d2f7846499")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { isOk() } }
    }

    @Test
    fun `should return code 204 when delete duelist`() {
        token = generateToken(UserRoleAuthority.EXODIA)
        mockMvc.delete(DUELIST_RESOURCE.plus("/4877c970-4c45-4a3d-be3a-77d2f7846499")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(role: UserRoleAuthority): String {
        val authorities = mutableListOf(Role(role.ordinal.toLong() + 1, role.name))
        val user = DuelistTest.buildToToken()
        return jwtConfig.generateToken(user.email, authorities)
    }
}