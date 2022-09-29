package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.PessoaPayloadDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.SalaRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    /**Busca todas as salas do banco de dados.
     * @return List<SalaDTO> - Retorna uma lista de DTO de todas as salas existentes.
     */
    public Object getAll(){
        return salaRepository.findAll().stream().map(SalaEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona nova sala ao banco de dados.
     * @param user Entidade do usuário que está acessando o método.
     * @param newSala Dados de uma nova sala.
     * @return Dados da salvos da nova sala.
     */
    public SalaDTO saveSala(@NotNull PessoaEntity user, @NotNull SalaPayloadDTO newSala) {
        if(user.isAdministrador()){
            salaRepository.save(newSala.toEntity(cinemaRepository.findById(newSala.getIdCinema()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"))));
            return salaRepository.findByNomeByCinema(newSala.getIdCinema(), newSala.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!")).toDTO();
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas para prorietários de cinemas!");
        }
    }

    /**Atualiza dados de sala já existente no banco de dados.
     * @param user Entidade do usuário que está acessando o método.
     * @param newSala Dados atualizados da sala.
     * @return Dados atualizados.
     */
    public SalaDTO update(@NotNull PessoaEntity user, @NotNull SalaDTO newSala) {
        if(user.isAdministrador()){
            SalaEntity salaEntity = salaRepository.findById(newSala.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"));
            if(newSala.getNome() != null) salaEntity.setNome(newSala.getNome());
            salaRepository.save(salaEntity);
            return salaEntity.toDTO();
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas para prorietários de cinemas!");
        }
    }

    /**Deleta sala do banco de dados.
     * @param user Entidade do usuário que está acessando o método.
     * @param id Código de identificação da sala que deve ser deletada.
     */
    public void delete(@NotNull PessoaEntity user, @NotNull Long id) {
        if(user.isAdministrador()){
            salaRepository.deleteById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas para prorietários de cinemas!");
        }
    }
}
