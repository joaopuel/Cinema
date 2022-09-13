package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.dto.PessoaPayloadDTO;
import com.entra21.grupo1.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaRestController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<PessoaDTO> getPessoas() {
        return pessoaService.getAll();
    }

    @PostMapping
    public void addPessoa(@RequestBody PessoaPayloadDTO newPessoa) {
        pessoaService.save(newPessoa);
    }

}
