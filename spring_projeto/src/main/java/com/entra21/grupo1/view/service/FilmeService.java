package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.GeneroEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public FilmeDTOWithDetails getByNome(String nome) {
        if(nome.contains("_")) {
            nome = nome.replaceAll("_", " ");
        }
        FilmeEntity f = filmeRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        return f.toDTOWithDetails();
    }

    //Adiciona filmes ao banco de dados
    public FilmeDTOWithDetails saveFilme(FilmePayLoadDTO input) {
        filmeRepository.save(input.toEntity());
        return filmeRepository.findByNome(input.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")).toDTOWithDetails();
    }

    //Atualiza filmes do banco de dados
    public FilmeDTOWithDetails update(FilmeDTOWithDetails newfilme) {
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

    public void addGeneros(GenerosFilmeDTO generosFilmeDTO) {
        filmeRepository.findById(generosFilmeDTO.getIdFilme()).ifPresentOrElse(f -> {
            Set<GeneroEntity> generos = new HashSet<>(generoRepository.findAllById(generosFilmeDTO.getIdGeneros()));
            f.setGeneros(generos);
            filmeRepository.save(f);
        }, () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!");});
    }
}
