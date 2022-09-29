package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
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
     * @return Lista de todas as salas existentes.
     */
    public List<SalaDTO> getAll(){
        return salaRepository.findAll().stream().map(SalaEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona uma nova sala ao banco de dados.
     * @param newSala Dados de uma nova sala.
     * @return Dados da salvos da nova sala.
     */
    public SalaDTO saveSala(@NotNull SalaPayloadDTO newSala) {
        salaRepository.save(newSala.toEntity(cinemaRepository.findById(newSala.getIdCinema()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"))));
        return salaRepository.findByNomeByCinema(newSala.getIdCinema(), newSala.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!")).toDTO();
    }

    /**Atualiza as informações de uma sala no banco de dados.
     * @param newSala Dados da sala que devem ser atualizados.
     * @return Dados atualizados da sala.
     */
    public SalaDTO update(@NotNull SalaDTO newSala) {
        SalaEntity salaEntity = salaRepository.findById(newSala.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"));
        if(newSala.getNome() != null) salaEntity.setNome(newSala.getNome());
        salaRepository.save(salaEntity);
        return salaEntity.toDTO();
    }

    /**Deleta uma sala do banco de dados.
     * @param id Número de identificação da sala que deve ser deletada.
     */
    public void delete(@NotNull Long id) {
        salaRepository.deleteById(id);
    }
}
