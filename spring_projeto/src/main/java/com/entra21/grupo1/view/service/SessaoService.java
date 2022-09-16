package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.entity.SessaoEntity;
import com.entra21.grupo1.view.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    public List<SessaoDTO> getAll(LocalDateTime dataSessao){
        List<SessaoEntity> list;
        if(dataSessao == null){
            list = sessaoRepository.findAll();
        }else{
            list = sessaoRepository.findAllByDataSessaoBetween(dataSessao.toLocalDate().atStartOfDay(), dataSessao.toLocalDate().plusDays(1).atStartOfDay());
        }
        return list.stream().map( sessao -> {
            SessaoDTO dto = new SessaoDTO();
            dto.setId(sessao.getId());
            dto.setDataSessao(sessao.getDataSessao());
            dto.setValorInteira(sessao.getValorInteira());
            dto.setValorMeia(sessao.getValorMeia());
            return dto;
        }).collect(Collectors.toList());
    }
}
