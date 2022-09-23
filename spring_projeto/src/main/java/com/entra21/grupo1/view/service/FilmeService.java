package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<FilmeDTO> getAll(String genero, Double nota) {
        List<FilmeEntity> list;
        if(nota != null){
            list = filmeRepository.findAllFilmesDeNotaComSessoesDepois(nota, LocalDateTime.now());
        }else if(genero != null){
            list = filmeRepository.findAllFilmesDeGeneroComSessoesDepois(genero, LocalDateTime.now());
        } else {
            list = filmeRepository.findAllFilmesComSessoesDepois(LocalDateTime.now());
        }

        return list.stream().map(FilmeEntity::toDTO).collect(Collectors.toList());
    }

    public FilmeDTO getByNome(String nome) {
        if(nome.contains("_")) {
            nome = nome.replaceAll("_", " ");
        }
        FilmeEntity f = filmeRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        return f.toDTOWithDetails();
    }

    public FilmeDTO saveFilme(FilmePayLoadDTO input) {
        filmeRepository.save(input.toEntity());
        return filmeRepository.findByNome(input.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")).toDTO();
    }
}
