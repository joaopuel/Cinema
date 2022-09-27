package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.IngressoPayloadDTO;
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
    public void addIngresso(@RequestBody IngressoPayloadDTO newIngresso){
        ingressoService.saveIngresso(newIngresso);
    }

    @DeleteMapping("/{id}")
    public void deleteIngresso(@PathVariable(name  = "id") Long id){
        ingressoService.delete(id);
    }
}
