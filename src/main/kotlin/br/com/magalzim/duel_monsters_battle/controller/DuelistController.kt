package br.com.magalzim.duel_monsters_battle.controller

import br.com.magalzim.duel_monsters_battle.form.NewDuelistForm
import br.com.magalzim.duel_monsters_battle.form.UpdateDuelistForm
import br.com.magalzim.duel_monsters_battle.service.DuelistService
import br.com.magalzim.duel_monsters_battle.view.DuelistView
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/duelists")
@SecurityRequirement(name="bearerAuth")
class DuelistController(private val service: DuelistService) {
    @GetMapping("/{id}")
    fun find(@PathVariable id: String) = service.find(id)

    @PostMapping
    @Transactional
    fun add(
        @RequestBody @Valid duelist: NewDuelistForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<DuelistView> {
        val view = service.add(duelist)
        val uri = uriBuilder.path("/duelists/${view.id}").build().toUri()
        return ResponseEntity.created(uri).body(view)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid duelist: UpdateDuelistForm): ResponseEntity<DuelistView>  {
        val view = service.update(duelist)
        return ResponseEntity.ok(view)
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable email: String) {
        service.delete(email)
    }
}