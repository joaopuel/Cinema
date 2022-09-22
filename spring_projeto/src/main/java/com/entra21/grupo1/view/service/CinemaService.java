package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.CinemaPayloadDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<CinemaDTO> getAll() {
        return cinemaRepository.findAll().stream().map( cinema -> {
            CinemaDTO dto = new CinemaDTO();

            dto.setId(cinema.getId());
            dto.setNome(cinema.getNome());
//            dto.setAdministrador(cinema.getAdministrador());

            dto.setSalas(cinema.getSalas().stream().map(salaEntity -> {
                SalaDTO salaDTO = new SalaDTO();
                salaDTO.setId(salaEntity.getId());
                salaDTO.setNome(salaEntity.getNome());
//                salaDTO.setCinema(salaEntity.getCinema().getId());
                return salaDTO;
            }).collect(Collectors.toList()));
            dto.setCaixa(cinema.getCaixa());


            return dto;
        }).collect(Collectors.toList());
    }

    public void save(CinemaDTO cinemaDTO) {
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setNome(cinemaDTO.getNome());


        cinemaEntity.setCaixa(cinemaDTO.getCaixa());
        cinemaRepository.save(cinemaEntity);
    }
}
