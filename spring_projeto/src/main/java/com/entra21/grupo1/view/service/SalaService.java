package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.PessoaPayloadDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.CinemaEntity;
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

    public List<SalaDTO> getAll(){
        return salaRepository.findAll().stream().map( sala -> {
            SalaDTO dto = new SalaDTO();
            dto.setNome(sala.getNome());
            CinemaDTO cinemaDTO = new CinemaDTO();
            dto.setCinema(cinemaDTO);
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(SalaPayloadDTO input) {
        SalaEntity newSala = new SalaEntity();
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setId(input.getCinema().getId());
        cinemaEntity.setNome(input.getCinema().getNome());
        newSala.setNome(input.getNome());
        newSala.setCinema(cinemaEntity);
        salaRepository.save(newSala);
    }
}
