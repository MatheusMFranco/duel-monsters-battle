package br.com.magalzim.duel_monsters_battle.exception

import java.lang.RuntimeException
import kotlin.reflect.KClass

class NotFoundException(entity: KClass<*>) : RuntimeException("${entity.simpleName} ID not found!")