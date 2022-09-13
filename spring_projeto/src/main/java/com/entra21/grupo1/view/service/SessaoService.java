package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.view.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    public List<SessaoDTO> getAll(){
        return sessaoRepository.findAll().stream().map( sessao -> {
            SessaoDTO dto = new SessaoDTO();
            dto.setId(sessao.getId());
            dto.setDataSessao(sessao.getDataSessao());
            dto.setValorInteira(sessao.getValorInteira());
            dto.setValorMeia(sessao.getValorMeia());
            return dto;
        }).collect(Collectors.toList());
    }
}
