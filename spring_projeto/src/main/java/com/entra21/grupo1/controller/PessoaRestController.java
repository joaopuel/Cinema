package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.MeusIngressosDTO;
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

    @GetMapping("/meusingressos")
    public List<MeusIngressosDTO> meusIngressos(@RequestParam(name = "id") Long id) {
        return pessoaService.meusIngressos(id);
    }

    @PostMapping
    public PessoaDTO addPessoa(@RequestBody PessoaPayloadDTO newPessoa) {
        return pessoaService.save(newPessoa);
    }

    @PutMapping
    public PessoaDTO updatePessoa(@RequestBody PessoaDTO newPessoa) {
        return pessoaService.update(newPessoa);
    }

    @DeleteMapping
    public void deletePessoa(@RequestParam(name = "id") Long id) {
        pessoaService.delete(id);
    }

}
