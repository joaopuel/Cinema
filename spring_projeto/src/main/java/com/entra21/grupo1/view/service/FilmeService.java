package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.SessaoEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

        return list.stream().map( f -> {
            FilmeDTO filmeDTO = new FilmeDTO();
            filmeDTO.setId(f.getId());
            filmeDTO.setNome(f.getNome());
            filmeDTO.setCartaz(f.getCartaz());

            filmeDTO.setSessoes(
                    f.getSessoes().stream().map( s -> {
                        SessaoDTO sessaoDTO = new SessaoDTO();
                        sessaoDTO.setId(s.getId());
                        sessaoDTO.setDataSessao(s.getDataSessao());
                        sessaoDTO.setValorMeia(s.getValorMeia());
                        sessaoDTO.setValorInteira(s.getValorInteira());
                        if(s.getDataSessao().toLocalDate().isAfter(LocalDateTime.now().toLocalDate()) || s.getDataSessao().toLocalDate().equals(LocalDateTime.now().toLocalDate())){
                            return sessaoDTO;
                        }else{
                            return null;
                        }
                    }).filter(Objects::nonNull).collect(Collectors.toList())
            );

            return filmeDTO;
        }).collect(Collectors.toList());
    }

    public FilmeDetailsDTO getByNome(String nome) {
        if(nome.contains("_")) {
            nome = nome.replaceAll("_", " ");
        }
        FilmeEntity f = filmeRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        FilmeDetailsDTO filmeDetailsDTO = new FilmeDetailsDTO();
        filmeDetailsDTO.setId(f.getId());
        filmeDetailsDTO.setNome(f.getNome());
        filmeDetailsDTO.setSinopse(f.getSinopse());
        filmeDetailsDTO.setDuracao(f.getDuracao());
        filmeDetailsDTO.setDiretor(f.getDiretor());
        filmeDetailsDTO.setCartaz(f.getCartaz());
        filmeDetailsDTO.setMediaNotas(f.getMedia());

        filmeDetailsDTO.setGeneros(
                f.getGeneros().stream().map( g -> {
                    GeneroDTO generoDTO = new GeneroDTO();
                    generoDTO.setId(g.getId());
                    generoDTO.setNome(g.getNome());
                    return generoDTO;
                }).collect(Collectors.toList())
        );

        filmeDetailsDTO.setSessoes(
                f.getSessoes().stream().map( s -> {
                    SessaoDTO sessaoDTO = new SessaoDTO();
                    sessaoDTO.setId(s.getId());
                    sessaoDTO.setDataSessao(s.getDataSessao());
                    sessaoDTO.setValorMeia(s.getValorMeia());
                    sessaoDTO.setValorInteira(s.getValorInteira());
                    if(s.getDataSessao().toLocalDate().isAfter(LocalDateTime.now().toLocalDate()) || s.getDataSessao().toLocalDate().equals(LocalDateTime.now().toLocalDate())){
                        return sessaoDTO;
                    }else{
                        return null;
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList())
        );

        filmeDetailsDTO.setAvaliacoes(
                f.getAvaliacoes().stream().map( a -> {
                    AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
                    avaliacaoDTO.setId(a.getId());
                    avaliacaoDTO.setRating(a.getRating());
                    avaliacaoDTO.setComentario(a.getComentario());
                    avaliacaoDTO.setDataAvaliacao(a.getDataAvaliacao());
                    avaliacaoDTO.setNomeUsuario(a.getUsuario().getNome());
                    avaliacaoDTO.setSobrenomeUsuario(a.getUsuario().getSobrenome());
                    return avaliacaoDTO;
                }).collect(Collectors.toList())
        );

        return filmeDetailsDTO;
    }

    public FilmeDTO saveFilme(FilmePayLoadDTO input) {
        FilmeEntity newfilme = new FilmeEntity();
        newfilme.setNome(input.getNome());
        newfilme.setDuracao(input.getDuracao());
        newfilme.setDiretor(input.getDiretor());
        newfilme.setCartaz(input.getCartaz());
        newfilme.setSinopse(input.getSinopse());
        //todo
        return null;
    }
}
