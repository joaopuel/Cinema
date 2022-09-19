package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.PessoaPayloadDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.view.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<SalaPayloadDTO> getAll(){
        return salaRepository.findAll().stream().map( sala -> {
            SalaPayloadDTO dto = new SalaPayloadDTO();
            dto.setNome(sala.getNome());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(SalaPayloadDTO input) {
        SalaEntity newSala = new SalaEntity();
        newSala.setNome(input.getNome());
        salaRepository.save(newSala);
    }
}
