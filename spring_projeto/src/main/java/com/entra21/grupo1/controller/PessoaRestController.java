package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class PessoaRestController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/meusdados")
    public PessoaDTO getDadosPessoa(){
        return pessoaService.getDados();
    }

    @GetMapping("/meusingressos")
    public List<IngressoDTO> getIngressosPessoa() {
        return pessoaService.getIngressos();
    }

    @GetMapping("/meuscinemas")
    public List<CinemaDTOWithDetails> getCinemasPessoa(){
        return pessoaService.getCinemas();
    }

    @PostMapping("/criarusuario")
    public void addPessoa(@RequestBody PessoaPayloadDTO newPessoa) {
        pessoaService.savePessoa(newPessoa);
    }

    @PutMapping("/atualizardados")
    public void updatePessoa(@RequestBody PessoaPayloadDTO newPessoa) {
        pessoaService.update(newPessoa);
    }

    @PutMapping("/movimentacao")
    public void addMovimentacao(@RequestBody Long valor) {
        pessoaService.movimentacao(valor);
    }

    @DeleteMapping("/delete")
    public void deletePessoa() {
        pessoaService.delete();
    }
}
