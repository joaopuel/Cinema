package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.dto.SessaoPayLoadDTO;
import com.entra21.grupo1.model.entity.SessaoEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.SalaRepository;
import com.entra21.grupo1.view.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private FilmeService filmeService;

    public List<SessaoDTO> getAll(LocalDateTime dataSessao){
        List<SessaoEntity> list;
        if(dataSessao == null){
            list = sessaoRepository.findAll();
        }else{
            list = sessaoRepository.findAllByDataSessaoBetween(dataSessao.toLocalDate().atStartOfDay(), dataSessao.toLocalDate().plusDays(1).atStartOfDay());
        }
        return list.stream().map(SessaoEntity::toDTO).collect(Collectors.toList());
    }

    public void saveSessao(SessaoPayLoadDTO newSessao) {
        sessaoRepository.save(toEntity(newSessao));
    }

    public SessaoEntity toEntity(SessaoPayLoadDTO s){
        SessaoEntity sessaoEntity = new SessaoEntity();
        sessaoEntity.setDataSessao(s.getDataSessao());
        sessaoEntity.setValorInteira(s.getValorInteira());
        sessaoEntity.setValorMeia(s.getValorMeia());
        sessaoEntity.setTipoSessao(s.getTipoSessao());
        sessaoEntity.setSala(salaRepository.findById(s.getIdSala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!")));
        sessaoEntity.setFilme(filmeRepository.findById(s.getIdFilme()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")));
        return sessaoEntity;
    }

    public List<SessaoDTO> getAllByFilme(String nome) {
        return filmeService.getByNome(nome).getSessoes();
    }
}
