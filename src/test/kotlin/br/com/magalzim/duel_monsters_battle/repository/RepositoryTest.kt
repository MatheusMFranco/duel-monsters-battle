package br.com.magalzim.duel_monsters_battle.repository

import br.com.magalzim.duel_monsters_battle.entity.DuelistTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
@Transactional
class RepositoryTest {

    @Autowired
    private lateinit var duelistRepository: DuelistRepository

    private val duelist = DuelistTest.build()

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testdb")
            withUsername("bakura")
            withPassword("123456")
            start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            mysqlContainer.stop()
        }
    }

    @Test
    fun `should find by email`() {
        val foundDuelists = duelistRepository.findByEmail(duelist.email)
        Assertions.assertThat(foundDuelists).isNotNull
    }
}