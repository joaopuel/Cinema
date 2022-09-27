package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.view.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaRestController {

    @Autowired
    private SalaService salaService;

    //Chama o método getAll do SalaService
    @GetMapping
    public List<SalaDTO> getSalas() {
        return salaService.getAll();
    }

    //Chama o método saveSala do SalaService
    @PostMapping
    public SalaDTO addSala(@RequestBody SalaPayloadDTO newSala) {
        return salaService.saveSala(newSala);
    }

    //Chama o método update do SalaService
    @PutMapping
    public SalaDTO updateSala(@RequestBody SalaDTO newSala) {
        return salaService.update(newSala);
    }

    //Chama o método delete do SalaService
    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable(name = "id") Long id) {salaService.delete(id);}
}
