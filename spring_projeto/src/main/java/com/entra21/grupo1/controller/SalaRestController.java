package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaRestController {

    @Autowired
    private SalaService salaService;

    //Chama o método getAll do SalaService
    @GetMapping
    public Object getSalas(@AuthenticationPrincipal PessoaEntity user) {
        //        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(user.isAdministrador()) {
            return salaService.getAll();
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    //Chama o método saveSala do SalaService
    @PostMapping
    public SalaDTO addSala(@AuthenticationPrincipal PessoaEntity user, @RequestBody SalaPayloadDTO newSala) {
        return salaService.saveSala(user, newSala);
    }

    //Chama o método update do SalaService
    @PutMapping
    public SalaDTO updateSala(@AuthenticationPrincipal PessoaEntity user, @RequestBody SalaDTO newSala) {
        return salaService.update(user, newSala);
    }

    //Chama o método delete do SalaService
    @DeleteMapping("/{id}")
    public void deletePessoa(@AuthenticationPrincipal PessoaEntity user, @PathVariable(name = "id") Long id) {
        salaService.delete(user, id);
    }
}
