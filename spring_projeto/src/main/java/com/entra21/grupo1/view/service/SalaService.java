package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.PessoaPayloadDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    //Busca todos as salas do banco de dados
    public List<SalaDTO> getAll(){
        return salaRepository.findAll().stream().map( sala -> {
            SalaDTO salaDTO = new SalaDTO();
            salaDTO.setId(sala.getId());
            salaDTO.setNome(sala.getNome());
            return salaDTO;
        }).collect(Collectors.toList());
    }

    //Adiciona novas salas ao banco de dados
    public void saveSala(SalaPayloadDTO input) {
        SalaEntity newSala = new SalaEntity();
        newSala.setNome(input.getNome());
        CinemaEntity cinemaEntity = cinemaRepository.findById(input.getIdCinema()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
        newSala.setCinema(cinemaEntity);
        salaRepository.save(newSala);
    }

    //Atualiza salas já existentes no banco de dados
    public SalaDTO update(SalaDTO newSala) {
        SalaEntity e = salaRepository.findById(newSala.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"));
        if(newSala.getNome() != null) e.setNome(newSala.getNome());
        salaRepository.save(e);
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setNome(e.getNome());
        return salaDTO;
    }

    //Delete salas do banco de dados
    public void delete(Long id) {salaRepository.deleteById(id);}
}
