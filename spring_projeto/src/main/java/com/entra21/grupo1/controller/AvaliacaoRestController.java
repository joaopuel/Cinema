package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.AvaliacaoDTO;
import com.entra21.grupo1.model.dto.AvaliacaoPayloadDTO;
import com.entra21.grupo1.view.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoRestController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public void addAvaliacao(@RequestBody AvaliacaoPayloadDTO newAvaliacao) {
        avaliacaoService.saveAvaliacao(newAvaliacao);
    }

    @PutMapping
    public AvaliacaoDTO updateAvaliacao(@RequestBody AvaliacaoDTO newAvaliacao){
        return avaliacaoService.update(newAvaliacao);
    }

    @DeleteMapping("/{id}")
    public void deleteAvaliacao(@PathVariable(name = "id") Long id) {avaliacaoService.delete(id);}
}
