package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.dto.SessaoPayLoadDTO;
import com.entra21.grupo1.model.dto.SessaoDTOWithDetails;
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

    //Busca todas as sessões do banco de dados
    public List<SessaoDTO> getAll(LocalDateTime dataSessao){
        List<SessaoEntity> list;
        if(dataSessao == null){
            list = sessaoRepository.findAll();
        }else{
            list = sessaoRepository.findAllByDataSessaoBetween(dataSessao.toLocalDate().atStartOfDay(), dataSessao.toLocalDate().plusDays(1).atStartOfDay());
        }
        return list.stream().map(SessaoEntity::toDTO).collect(Collectors.toList());
    }

    //Adiciona todas as sessões ao banco de dados
    public void saveSessao(SessaoPayLoadDTO newSessao) {
        sessaoRepository.save(
                newSessao.toEntity(
                        filmeRepository.findById(newSessao.getIdFilme()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")),
                        salaRepository.findById(newSessao.getIdSala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"))
                )
        );
    }

    public SessaoDTO update(SessaoDTO newSessao) {
        SessaoEntity sessaoEntity = sessaoRepository.findById(newSessao.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!"));
        if(newSessao.getDataSessao() != null){ sessaoEntity.setDataSessao(newSessao.getDataSessao());}
        if(newSessao.getValorInteira() != null){ sessaoEntity.setValorInteira(newSessao.getValorInteira());}
        if(newSessao.getValorMeia() != null){ sessaoEntity.setValorMeia(newSessao.getValorMeia());}
        if(newSessao.getTipoSessao() != null){ sessaoEntity.setTipoSessao(newSessao.getTipoSessao());}
        sessaoRepository.save(sessaoEntity);
        return sessaoEntity.toDTO();
    }

    public void delete(Long id) {
        sessaoRepository.deleteById(id);
    }

    public SessaoDTOWithDetails getById(Long id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!")).toDTOWithDetails();
    }
}
