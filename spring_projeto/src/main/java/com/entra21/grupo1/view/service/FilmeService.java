package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private GeneroRepository generoRepository;

    //Busca todos os filmes do banco de dados
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

    //Busca os filmes por nome
    public FilmeDTO getByNome(String nome) {
        if(nome.contains("_")) {
            nome = nome.replaceAll("_", " ");
        }
        FilmeEntity f = filmeRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        return f.toDTOWithDetails();
    }

    //Adiciona filmes ao banco de dados
    public FilmeDTO saveFilme(FilmePayLoadDTO input) {
        filmeRepository.save(input.toEntity(generoRepository.findById(input.getIdGeneros()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero não encontrado!"))));
        return filmeRepository.findByNome(input.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")).toDTOWithDetails();
    }

    //Atualiza filmes do banco de dados
    public FilmeDTO update(FilmeDTO newfilme) {
        FilmeEntity filmeEntity = filmeRepository.findById(newfilme.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        if (newfilme.getNome() != null){ filmeEntity.setNome(newfilme.getNome());}
        if (newfilme.getDuracao() != null){ filmeEntity.setDuracao(newfilme.getDuracao());}
        if (newfilme.getSinopse() != null){ filmeEntity.setSinopse(newfilme.getSinopse());}
        if (newfilme.getDiretor() != null){ filmeEntity.setDiretor(newfilme.getDiretor());}
        if (newfilme.getCartaz() != null){ filmeEntity.setCartaz(newfilme.getCartaz());}
        filmeRepository.save(filmeEntity);
        return filmeEntity.toDTOWithDetails();
    }

    //Deleta filmes do banco de dados
    public void delete(Long id) {
        filmeRepository.deleteById(id);
    }
}
