package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.CadeiraPayloadDTO;
import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.dto.SessaoPayloadDTO;
import com.entra21.grupo1.model.dto.SessaoDTOWithDetails;
import com.entra21.grupo1.view.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoRestController {

    @Autowired
    private SessaoService sessaoService;

    @GetMapping
    public List<SessaoDTO> getAllSessoes() {
        return sessaoService.getAll();
    }

    @GetMapping("/{id}")
    public SessaoDTOWithDetails getSessaoById(@PathVariable(name = "id") Long id){
        return sessaoService.getById(id);
    }

    @PostMapping
    public void addSessao(@RequestBody SessaoPayloadDTO newSessao){
        sessaoService.saveSessao(newSessao);
    }

    @PutMapping
    public void updateSessao(@RequestBody SessaoDTO newSessao) throws NoSuchFieldException {
        sessaoService.update(newSessao);
    }

    @DeleteMapping("/{id}")
    public void deleteSessao(@PathVariable(name = "id") Long id){
        sessaoService.delete(id);
    }

    @PostMapping("/listasessoes")
    public void addListaSessoes(@RequestBody List<SessaoPayloadDTO> newSessoes) {
        newSessoes.forEach( (c) -> {sessaoService.saveSessao(c);} );
    }
}
