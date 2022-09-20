package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.CinemaPayloadDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<CinemaDTO> getAll() {
        return cinemaRepository.findAll().stream().map( cinema -> {
            CinemaDTO dto = new CinemaDTO();

            dto.setId(cinema.getId());
            dto.setNome(cinema.getNome());
            dto.setAdministrador(cinema.getAdministrador().toPessoaDTO());
            dto.setCaixa(cinema.getCaixa());
            dto.setSalas(cinema.getSalas().stream().map(salaEntity -> {
                SalaDTO salaDTO = new SalaDTO();
                salaDTO.setId(salaEntity.getId());
                salaDTO.setNome(salaEntity.getNome());
                return salaDTO;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(CinemaPayloadDTO cinemaPayloadDTO) {
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setNome(cinemaPayloadDTO.getNome());
        PessoaEntity pessoa = pessoaRepository.findById(cinemaPayloadDTO.getAdministrador()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"));
        cinemaEntity.setAdministrador(pessoa);
        cinemaEntity.setCaixa(cinemaPayloadDTO.getCaixa());
        cinemaRepository.save(cinemaEntity);
    }

    public CinemaDTO update(CinemaDTO cinemaDTO) {
        CinemaEntity cinemaEntity = cinemaRepository.findById(cinemaDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
        if(cinemaDTO.getNome() != null) cinemaEntity.setNome(cinemaDTO.getNome());
        //administrador
        //salas
        if(cinemaDTO.getCaixa() != null) cinemaEntity.setCaixa(cinemaDTO.getCaixa());
        cinemaRepository.save(cinemaEntity);

        CinemaDTO c = new CinemaDTO();
        c.setNome(cinemaEntity.getNome());
        //administrador
        //salas
        c.setCaixa(cinemaEntity.getCaixa());
        return c;
    }
}
