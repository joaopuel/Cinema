package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.IngressoPayLoadDTO;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.IngressoRepository;
import com.entra21.grupo1.view.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoRestController {

    @Autowired
    private IngressoService ingressoService;

    @GetMapping
    public List<IngressoDTO> getAllIngressos(){
        return ingressoService.getAll();
    }

    @PostMapping
    public void addIngresso(@RequestBody IngressoPayLoadDTO newIngresso){
        ingressoService.saveIngresso(newIngresso);
    }

    @DeleteMapping("/{id}")
    public void deleteIngresso(@PathVariable(name  = "id") Long id){
        ingressoService.delete(id);
    }
}
