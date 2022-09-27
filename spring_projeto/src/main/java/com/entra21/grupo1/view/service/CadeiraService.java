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

    //Busca todas as cadeiras do banco de dados
    public List<CadeiraDTO> getAll(){
        return cadeiraRepository.findAll().stream().map(CadeiraEntity::toDTO).collect(Collectors.toList());
    }

    //Adiciona cadeira ao banco de dados
    public void saveCadeira(CadeiraPayloadDTO input) {
        cadeiraRepository.save(input.toEntity(salaRepository.findById(input.getIdSala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"))));
    }

    //Atualiza cadeiras já existentes do banco de dados
    public CadeiraDTO update(CadeiraDTO newCadeira) {
        CadeiraEntity cadeiraEntity = cadeiraRepository.findById(newCadeira.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadeira não encontrada!"));

        if(newCadeira.getCodigo() != null) cadeiraEntity.setCodigo(newCadeira.getCodigo());
        if(newCadeira.getTipoCadeira() != null) cadeiraEntity.setTipoCadeira(newCadeira.getTipoCadeira());
        if(newCadeira.getFileira() != null) cadeiraEntity.setFileira(newCadeira.getFileira());
        if(newCadeira.getOrdemFileira() != null) cadeiraEntity.setOrdemFileira(newCadeira.getOrdemFileira());

        cadeiraRepository.save(cadeiraEntity);

        return cadeiraEntity.toDTO();
    }

    //Deleta cadeiras do banco de dados
    public void delete(Long id) {cadeiraRepository.deleteById(id);}
}
