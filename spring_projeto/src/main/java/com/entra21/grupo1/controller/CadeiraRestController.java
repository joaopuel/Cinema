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

    //Chama o método getAll do CadeiraService
    @GetMapping
    public List<CadeiraDTO> getCadeiras() {
        return cadeiraService.getAll();
    }

    @GetMapping("/{id}")
    public List<SessaoDTO> getAllSessoesByFilme(@PathVariable(name = "id") Long id){
        //todo
        return null;
    }

    //Chama o método saveCadeira do CadeiraService
    @PostMapping
    public void addCadeira(@RequestBody CadeiraPayloadDTO newCadeira) {
        cadeiraService.saveCadeira(newCadeira);
    }

    //Chama o método update do CadeiraService
    @PutMapping
    public CadeiraDTO updateCadeira(@RequestBody CadeiraDTO newCadeira) {
        return cadeiraService.update(newCadeira);
    }
    //Chama o método delete do CadeiraService
    @DeleteMapping
    public void deletePessoa(@RequestParam(name = "id") Long id) {cadeiraService.delete(id);}
}

