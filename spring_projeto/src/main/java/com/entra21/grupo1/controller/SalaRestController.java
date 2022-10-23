package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaRestController {

    @Autowired
    private SalaService salaService;

    //Chama o método getAll do SalaService
    @GetMapping("/{id}")
    public SalaDTO getSalaById(@PathVariable(name = "id") Long id) {
        return salaService.getById(id);
    }

    //Chama o método saveSala do SalaService
    @PostMapping
    public SalaDTO addSala(@RequestBody SalaPayloadDTO newSala) {
        return salaService.saveSala(newSala);
    }

    //Chama o método update do SalaService
    @PutMapping
    public void updateSala(@AuthenticationPrincipal PessoaEntity user, @RequestBody SalaDTO newSala) throws NoSuchFieldException {
        salaService.update(newSala);
    }

    //Chama o método delete do SalaService
    @DeleteMapping("/{id}")
    public void deletePessoa(@AuthenticationPrincipal PessoaEntity user, @PathVariable(name = "id") Long id) {
        salaService.delete(id);
    }
}
