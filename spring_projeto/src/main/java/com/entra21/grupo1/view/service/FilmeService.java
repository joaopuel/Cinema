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

    @Autowired
    private PessoaService pessoaService;

    /**Método retornará todos os filmes a partir da data atual. Caso receba um parâmetro gênero, os filmes serão filtrados de acordo com o gênero, e caso receba um parâmetro nota, serão filtrados por nota.
     * Caso receba os dois parâmetros, será filtrado apenas por nota.
     * @param genero String - Gênero do filme.
     * @param nota Double - Nota do filme.
     * @return List<FilmeDTO> - Retorna uma lista de DTO de todos os filmes.
     */
    public List<FilmeDTO> getAll(String nome, String genero, Double nota) {
        List<FilmeEntity> list;
        if(genero != null && nota != null){
            list = filmeRepository.findAllFilmesDeGeneroENotaComSessoesDepois(genero, nota,  LocalDateTime.now().toLocalDate().atStartOfDay());
        }else if(nota != null){
            list = filmeRepository.findAllFilmesDeNotaComSessoesDepois(nota, LocalDateTime.now().toLocalDate().atStartOfDay());
        }else if(genero != null){
            list = filmeRepository.findAllFilmesDeGeneroComSessoesDepois(genero, LocalDateTime.now().toLocalDate().atStartOfDay());
        } else {
            list = filmeRepository.findAllFilmesComSessoesDepois(LocalDateTime.now().toLocalDate().atStartOfDay());
        }

        if(nome != null){
            list = list.stream().filter((f) -> f.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
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
        return filmeRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")).toDTOWithDetails();
    }

    public List<FilmeDTO> getAllByCinema(@NotNull Long id) {
        return filmeRepository.findAllByCinema(id, LocalDateTime.now().toLocalDate().atStartOfDay()).stream().map(FilmeEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona filme ao banco de dados.
     * @param newFilme FilmePayloadDTO - Dados de um novo filme.
     * @return FilmeDTOWithDetails - Dados salvos do filme com mais detalhes.
     */
    public FilmeDTOWithDetails saveFilme(@NotNull FilmePayloadDTO newFilme) {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullField(newFilme);
        filmeRepository.findByNome(newFilme.getNome()).ifPresentOrElse(
                (f) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse filme já existe!");
                },
                () -> {
                    filmeRepository.save(newFilme.toEntity());
                }
        );
        return filmeRepository.findByNome(newFilme.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")).toDTOWithDetails();
    }

    /**Adiciona gêneros de um filme.
     * @param generosFilmeDTO GenerosFilmeDTO - Gêneros que um filme pode ter.
     */
    public void addGeneros(@NotNull GenerosFilmeDTO generosFilmeDTO) {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullField(generosFilmeDTO);
        filmeRepository.findById(generosFilmeDTO.getIdFilme()).ifPresentOrElse(f -> {
            Set<GeneroEntity> generos = new HashSet<>(generoRepository.findAllById(generosFilmeDTO.getIdGeneros()));
            f.setGeneros(generos);
            filmeRepository.save(f);
        }, () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!");});
    }

    /**Atualiza filme existente do banco de dados.
     * @param newFilme FilmeDTOWithDetails - Dados de um filme que será atualizado.
     * @return CinemaDTO - Dados atualizados do filme.
     */
    public void update(@NotNull FilmeDTOWithDetails newFilme) throws NoSuchFieldException {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullId(newFilme);
        FilmeEntity filmeEntity = filmeRepository.findById(newFilme.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        if (newFilme.getNome() != null){ filmeEntity.setNome(newFilme.getNome());}
        if (newFilme.getDuracao() != null){ filmeEntity.setDuracao(newFilme.getDuracao());}
        if (newFilme.getSinopse() != null){ filmeEntity.setSinopse(newFilme.getSinopse());}
        if (newFilme.getDiretor() != null){ filmeEntity.setDiretor(newFilme.getDiretor());}
        if (newFilme.getCartaz() != null){ filmeEntity.setCartaz(newFilme.getCartaz());}
        filmeRepository.findByNome(newFilme.getNome()).ifPresentOrElse(
                (f) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse filme já existe!");
                },
                () -> {
                    filmeRepository.save(filmeEntity);
                }
        );
    }

    /**Deleta filmes do banco de dados.
     * @param id Long - Identificador de um filme existente.
     */
    public void delete(@NotNull Long id) {
        pessoaService.userIsAnAdministrador();
        filmeRepository.delete(filmeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")));
    }
}
