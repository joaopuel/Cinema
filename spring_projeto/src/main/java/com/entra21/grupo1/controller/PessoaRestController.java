package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.IngressoDTO;
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

    @GetMapping("/{nome}/meusdados")
    public PessoaDTO getDadosPessoa(@PathVariable(name = "nome") String nome){
        return pessoaService.getDados(nome);
    }

    @GetMapping("/{nome}/meusingressos")
    public List<IngressoDTO> getIngressosPessoa(@PathVariable(name = "nome") String nome) {
        return pessoaService.getIngressos(nome);
    }

    @GetMapping("/{nome}/meuscinemas")
    public List<CinemaDTO> getCinemasPessoa(@PathVariable(name = "nome") String nome) {
        return pessoaService.getCinemas(nome);
    }

    @PostMapping
    public PessoaDTO addPessoa(@RequestBody PessoaPayloadDTO newPessoa) {
        return pessoaService.save(newPessoa);
    }

    @PutMapping
    public PessoaDTO updatePessoa(@RequestBody PessoaDTO newPessoa) {
        return pessoaService.update(newPessoa);
    }

    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable(name = "id") Long id) {
        pessoaService.delete(id);
    }

}
