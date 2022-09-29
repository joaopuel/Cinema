package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.dto.PessoaPayloadDTO;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class PessoaRestController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/meusdados")
    public PessoaDTO getDadosPessoa(@AuthenticationPrincipal PessoaEntity user){
        return pessoaService.getDados(user);
    }

    @GetMapping("/meusingressos")
    public List<IngressoDTO> getIngressosPessoa(@AuthenticationPrincipal PessoaEntity user) {
        return pessoaService.getIngressos(user);
    }

    @GetMapping("/meuscinemas")
    public List<CinemaDTO> getCinemasPessoa(@AuthenticationPrincipal PessoaEntity user) {
        return pessoaService.getCinemas(user);
    }

    @PostMapping("/criarusuario")
    public PessoaDTO addPessoa(@RequestBody PessoaPayloadDTO newPessoa) {
        return pessoaService.savePessoa(newPessoa);
    }

    @PostMapping("/criaradministrador")
    public PessoaDTO addAdministrador(@AuthenticationPrincipal PessoaEntity user, @RequestBody PessoaPayloadDTO newPessoa){
        if(user.isAdministrador()){
            return pessoaService.saveAdministrador(newPessoa);
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas para administradores de cinemas!");
        }
    }

    @PutMapping("/atualizardados")
    public PessoaDTO updatePessoa(@AuthenticationPrincipal PessoaEntity user, @RequestBody PessoaPayloadDTO newPessoa) {
        return pessoaService.update(user, newPessoa);
    }

    @PutMapping("/deposito")
    public void addDeposito(@AuthenticationPrincipal PessoaEntity user, @RequestBody Double valor) {
        pessoaService.deposito(user, valor);
    }

    @PutMapping("/retirada")
    public void addRetirada(@AuthenticationPrincipal PessoaEntity user, @RequestBody Double valor) {
        pessoaService.retirada(user, valor);
    }

    @DeleteMapping("/delete")
    public void deletePessoa(@AuthenticationPrincipal PessoaEntity user) {
        pessoaService.delete(user);
    }

}
