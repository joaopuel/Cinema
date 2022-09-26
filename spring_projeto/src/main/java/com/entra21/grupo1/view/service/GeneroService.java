package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.GeneroDTO;
import com.entra21.grupo1.model.dto.GeneroPayloadDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.GeneroEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.GeneroRepository;
import com.entra21.grupo1.view.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    //Busca todos os generos do banco de dados
    public List<GeneroDTO> getAll(){
        return generoRepository.findAll().stream().map( genero -> {
            GeneroDTO generoDTO = new GeneroDTO();
            generoDTO.setId(genero.getId());
            generoDTO.setNome(genero.getNome());
            return generoDTO;
        }).collect(Collectors.toList());
    }

    //Adiciona novos generos ao banco de dados
    public void saveGenero(GeneroPayloadDTO input) {
        GeneroEntity newGenero = new GeneroEntity();
        newGenero.setNome(input.getNome());
        generoRepository.save(newGenero);
    }

    //Atualiza generos já existentes no banco de dados
    public GeneroDTO update(GeneroDTO newGenero) {
        GeneroEntity e = generoRepository.findById(newGenero.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero não encontrado!"));
        if(newGenero.getNome() != null) e.setNome(newGenero.getNome());
        generoRepository.save(e);
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(e.getId());
        generoDTO.setNome(e.getNome());
        return generoDTO;
    }

    //Delete generos do banco de dados
    public void delete(Long id) {generoRepository.deleteById(id);}
}
