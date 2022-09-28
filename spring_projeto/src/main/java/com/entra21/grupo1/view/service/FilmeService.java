package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.GeneroEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.GeneroRepository;
import com.sun.istack.NotNull;
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

    /**Método retornará todos os filmes a partir da data atual. Caso receba um parâmetro gênero, os filmes serão filtrados de acordo com o gênero, e caso receba um parâmetro nota, serão filtrados por nota.
     * Caso receba os dois parâmetros, será filtrado apenas por nota.
     * @param genero String - Gênero do filme.
     * @param nota Double - Nota do filme.
     * @return List<FilmeDTO> - Retorna uma lista de DTO de todos os filmes.
     */
    public List<FilmeDTO> getAll(String genero, Double nota) {
        List<FilmeEntity> list;
        if(genero != null && nota != null){
            list = filmeRepository.findAllFilmesDeGeneroENotaComSessoesDepois(genero, nota, LocalDateTime.now());
        }else if(nota != null){
            list = filmeRepository.findAllFilmesDeNotaComSessoesDepois(nota, LocalDateTime.now());
        }else if(genero != null){
            list = filmeRepository.findAllFilmesDeGeneroComSessoesDepois(genero, LocalDateTime.now());
        } else {
            list = filmeRepository.findAllFilmesComSessoesDepois(LocalDateTime.now());
        }

        return list.stream().map(FilmeEntity::toDTO).collect(Collectors.toList());
    }

    /**Busca um filme pelo seu nome completo.
     * @param nome String - Nome do filme.
     * @return FilmeDTOWithDetails - Dados do filme.
     */
    public FilmeDTOWithDetails getByNome(@NotNull String nome) {
        if(nome.contains("_")) {
            nome = nome.replaceAll("_", " ");
        }
        FilmeEntity f = filmeRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        return f.toDTOWithDetails();
    }

    /**Adiciona filme ao banco de dados.
     * @param input FilmePayloadDTO - Dados de um novo filme.
     * @return FilmeDTOWithDetails - Dados salvos do filme com mais detalhes.
     */
    public FilmeDTOWithDetails saveFilme(@NotNull FilmePayloadDTO input) {
        filmeRepository.save(input.toEntity());
        return filmeRepository.findByNome(input.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")).toDTOWithDetails();
    }

    /**Atualiza filme existente do banco de dados.
     * @param newfilme FilmeDTOWithDetails - Dados de um filme que será atualizado.
     * @return CinemaDTO - Dados atualizados do filme.
     */
    public FilmeDTOWithDetails update(@NotNull FilmeDTOWithDetails newfilme) {
        FilmeEntity filmeEntity = filmeRepository.findById(newfilme.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        if (newfilme.getNome() != null){ filmeEntity.setNome(newfilme.getNome());}
        if (newfilme.getDuracao() != null){ filmeEntity.setDuracao(newfilme.getDuracao());}
        if (newfilme.getSinopse() != null){ filmeEntity.setSinopse(newfilme.getSinopse());}
        if (newfilme.getDiretor() != null){ filmeEntity.setDiretor(newfilme.getDiretor());}
        if (newfilme.getCartaz() != null){ filmeEntity.setCartaz(newfilme.getCartaz());}
        filmeRepository.save(filmeEntity);
        return filmeEntity.toDTOWithDetails();
    }

    /**Deleta filmes do banco de dados.
     * @param id Long - Identificador de um filme existente.
     */
    public void delete(@NotNull Long id) {
        filmeRepository.deleteById(id);
    }

    /**Adiciona gêneros de um filme.
     * @param generosFilmeDTO GenerosFilmeDTO - Gêneros que um filme pode ter.
     */
    public void addGeneros(@NotNull GenerosFilmeDTO generosFilmeDTO) {
        filmeRepository.findById(generosFilmeDTO.getIdFilme()).ifPresentOrElse(f -> {
            Set<GeneroEntity> generos = new HashSet<>(generoRepository.findAllById(generosFilmeDTO.getIdGeneros()));
            f.setGeneros(generos);
            filmeRepository.save(f);
        }, () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!");});
    }
}
