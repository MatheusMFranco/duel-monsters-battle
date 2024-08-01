package br.com.magalzim.duel_monsters_battle

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class DuelMonstersBattleApplicationTests {

	@Test
	fun contextLoads() {
	}

}
