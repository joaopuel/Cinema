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
import com.sun.istack.NotNull;
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

    @Autowired
    private PessoaService pessoaService;

    /**Busca todos os gêneros do banco de dados.
     * @return List<GeneroDTO> - Retorna uma lista de DTO de todos os gêneros existentes.
     */
    public List<GeneroDTO> getAll(){
        return generoRepository.findAll().stream().map(GeneroEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona um novo gênero ao banco de dados.
     * @param newGenero GeneroPayloadDTO - Dados de um novo gênero.
     */
    public void saveGenero(@NotNull GeneroPayloadDTO newGenero) {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullField(newGenero);
        generoRepository.findByNome(newGenero.getNome()).ifPresentOrElse(
                (g) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse gênero já existe!");
                },
                () -> {
                    generoRepository.save(newGenero.toEntity());
                }
        );
    }

    /**Atualiza gênero já existentes no banco de dados.
     * @param newGenero GeneroDTO - Dados de um gênero que será atualizado.
     * @return GeneroDTO - Dados atualizados do gênero.
     */
    public void update(@NotNull GeneroDTO newGenero) throws NoSuchFieldException {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullId(newGenero);
        GeneroEntity generoEntity = generoRepository.findById(newGenero.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero não encontrado!"));
        if(newGenero.getNome() != null) generoEntity.setNome(newGenero.getNome());
        generoRepository.findByNome(newGenero.getNome()).ifPresentOrElse(
                (g) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Esse gênero já existe!");
                },
                () -> {
                    generoRepository.save(generoEntity);
                }
        );
    }

    /**Deleta gênero do banco de dados.
     * @param id Long - Identificador de um gênero existente.
     */
    public void delete(@NotNull Long id) {
        pessoaService.userIsAnAdministrador();
        generoRepository.delete(generoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado!")));
    }
}
