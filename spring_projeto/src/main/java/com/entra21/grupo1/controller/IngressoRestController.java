package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoRestController {

    @Autowired
    private IngressoRepository ingressoRepository;

    @GetMapping
    public List<IngressoDTO> getIngressosByIdPessoa(@RequestParam(name = "idPessoa") Long idPessoa){
        return ingressoRepository.findAllByIdPessoa(idPessoa);
    }

}
