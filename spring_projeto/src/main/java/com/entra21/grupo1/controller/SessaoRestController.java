package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.dto.SessaoPayLoadDTO;
import com.entra21.grupo1.view.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/filmes/sessoes")
public class SessaoRestController {

    @Autowired
    private SessaoService sessaoService;

    @GetMapping
    public List<SessaoDTO> getAllSessoes(@RequestParam(name = "dataSessao", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataSessao) {
        return sessaoService.getAll(dataSessao);
    }

    @PostMapping
    public void addSessao(@RequestBody SessaoPayLoadDTO newSessao){
        sessaoService.saveSessao(newSessao);
    }
}
