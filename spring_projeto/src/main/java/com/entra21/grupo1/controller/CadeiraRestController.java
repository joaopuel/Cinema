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

    /**
     * Chama método getAll de CadeiraService
     * @return List<CadeiraDTO>
     */
    @GetMapping
    public List<CadeiraDTO> getCadeiras() {
        return cadeiraService.getAll();
    }

    /**
     * Chama o método saveCAdeira de CadeiraService
     * @param newCadeira no formato CadeiraPayloadDTO
     */
    @PostMapping
    public void addCadeira(@RequestBody CadeiraPayloadDTO newCadeira) {
        cadeiraService.saveCadeira(newCadeira);
    }

    /**
     * Chama o método update de CadeiraService
     * @param newCadeira no formato CadeiraDTO
     * @return retorna um objeto CadeiraDTO com todas as informaçoes finais.
     */
    @PutMapping
    public CadeiraDTO updateCadeira(@RequestBody CadeiraDTO newCadeira) {
        return cadeiraService.update(newCadeira);
    }

    /**
     * Chama o método delete de CadeiraService
     * @param id no formato Long
     */
    @DeleteMapping
    public void deletePessoa(@RequestParam(name = "id") Long id) {cadeiraService.delete(id);}
}

