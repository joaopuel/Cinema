package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.view.service.CadeiraService;
import com.entra21.grupo1.view.service.SalaService;
import com.entra21.grupo1.view.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cadeiras")
public class CadeiraRestController {

    @Autowired
    private CadeiraService cadeiraService;

    @GetMapping
    public List<CadeiraDTO> getCadeiras() {
        return cadeiraService.getAll();
    }

    @PostMapping
    public void addCadeira(@RequestBody CadeiraPayloadDTO newCadeira) {
        cadeiraService.saveSala(newCadeira);
    }

    @PutMapping
    public CadeiraDTO updateCadeira(@RequestBody CadeiraDTO newCadeira) {
        return cadeiraService.update(newCadeira);
    }
}

