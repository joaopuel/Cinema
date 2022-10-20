package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class DefaultRestController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public String getUsername() {
        return ((PessoaEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().toString();
    }

    @PostMapping
    @RequestMapping("/cadastro")
    public void cadastro(@RequestBody PessoaDTO dto) {
        service.criar(dto);
    }
}
