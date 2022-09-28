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
    public List<SalaDTO> getAll(){
        return salaRepository.findAll().stream().map(SalaEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona novas salas ao banco de dados.
     * @param newSala SalaPayloadDTO - Dados de uma nova sala.
     */
    public SalaDTO saveSala(@NotNull SalaPayloadDTO newSala) {
        salaRepository.save(newSala.toEntity(cinemaRepository.findById(newSala.getIdCinema()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"))));
        return salaRepository.findByNomeByCinema(newSala.getIdCinema(), newSala.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!")).toDTO();
    }

    /**Atualiza salas já existentes no banco de dados.
     * @param newSala SalaDTO - Dados de uma sala que será atualizada.
     * @return SalaDTO - Dados atualizados da sala.
     */
    public SalaDTO update(@NotNull SalaDTO newSala) {
        SalaEntity salaEntity = salaRepository.findById(newSala.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"));
        if(newSala.getNome() != null) salaEntity.setNome(newSala.getNome());
        salaRepository.save(salaEntity);
        return salaEntity.toDTO();
    }

    /**Deleta salas do banco de dados.
     * @param id Long - identificador de uma sala existente.
     */
    public void delete(@NotNull Long id) {salaRepository.deleteById(id);}
}
