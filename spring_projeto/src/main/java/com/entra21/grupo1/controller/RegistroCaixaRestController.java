package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.view.service.CinemaService;
import com.entra21.grupo1.view.service.PessoaService;
import com.entra21.grupo1.view.service.RegistroCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class RegistroCaixaRestController {

    @Autowired
    private RegistroCaixaService registroCaixaService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/historico")
    public List<RegistroCaixaDTO> getAllRegistros(){
        return registroCaixaService.getAll();
    }

    @PostMapping
    public void addRegistro(@RequestBody RegistroCaixaPayloadDTO registro){
        pessoaService.userIsAnAdministrador();
        registroCaixaService.addRegistro(registro);
    }
}
