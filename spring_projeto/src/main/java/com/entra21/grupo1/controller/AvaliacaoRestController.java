package com.entra21.grupo1.controller;

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
    public AvaliacaoPayloadDTO addAvaliacoes(@RequestBody AvaliacaoPayloadDTO avaliacaoPayloadDTO) {
        return avaliacaoService.salvarAvaliacao(avaliacaoPayloadDTO);
    }

    @DeleteMapping
    public void deleteAvaliacao(@RequestParam(name = "id") Long id) {avaliacaoService.delete(id);}
}
