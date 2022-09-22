package com.entra21.grupo1.view.service;
import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.CadeiraEntity;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.view.repository.CadeiraRepository;
import com.entra21.grupo1.view.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadeiraService {

    @Autowired
    private CadeiraRepository cadeiraRepository;

    @Autowired
    private SalaRepository salaRepository;

    public List<CadeiraDTO> getAll(){
        return cadeiraRepository.findAll().stream().map( cadeira -> {
            CadeiraDTO cadeiraDTO= new CadeiraDTO();
            cadeiraDTO.setId(cadeira.getId());
            cadeiraDTO.setTipoCadeira(cadeira.getTipoCadeira());
            cadeiraDTO.setCodigo(cadeira.getCodigo());
            cadeiraDTO.setFileira(cadeira.getFileira());
            cadeiraDTO.setOrdemFileira(cadeira.getOrdemFileira());
            return cadeiraDTO;
        }).collect(Collectors.toList());
    }

    public void saveSala(CadeiraPayloadDTO input) {
        CadeiraEntity newCadeira = new CadeiraEntity();
        newCadeira.setCodigo(input.getCodigo());
        newCadeira.setTipoCadeira(input.getTipoCadeira());
        newCadeira.setFileira(input.getFileira());
        newCadeira.setOrdemFileira(input.getOrdemFileira());
        SalaEntity salaEntity = salaRepository.findById(input.getIdSala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"));
        newCadeira.setSala(salaEntity);
        cadeiraRepository.save(newCadeira);
    }

    public CadeiraDTO update(CadeiraDTO newCadeira) {
        CadeiraEntity e = cadeiraRepository.findById(newCadeira.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"));

        if(newCadeira.getCodigo() != null) e.setCodigo(newCadeira.getCodigo());
        if(newCadeira.getTipoCadeira() != null) e.setTipoCadeira(newCadeira.getTipoCadeira());
        if(newCadeira.getFileira() != null) e.setFileira(newCadeira.getFileira());
        if(newCadeira.getOrdemFileira() != null) e.setOrdemFileira(newCadeira.getOrdemFileira());

        if(newCadeira.getSala() != null) e.setSala(newCadeira.getSala().toEntity());
        cadeiraRepository.save(e);

        return newCadeira;
    }

}
