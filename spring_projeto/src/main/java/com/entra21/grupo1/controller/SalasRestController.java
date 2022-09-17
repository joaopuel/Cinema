package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.view.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalasRestController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<SalaDTO> getSalas() {
        return salaService.getAll();
    }

    @PostMapping
    public void addSala(@RequestBody SalaPayloadDTO newSala) {
        salaService.save(newSala);
    }
}
