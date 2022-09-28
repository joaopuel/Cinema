package com.entra21.grupo1.view.service;
import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    /**Busca todos os cinemas do banco de dados.
     * @return List<CinemaDTO> - Retorna uma lista de DTO de todos os cinemas existentes.
     */
    public List<CinemaDTO> getAll() {
        return cinemaRepository.findAll().stream().map(CinemaEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona cinema ao banco de dados.
     * @param newCinema CinemaPayloadDTO - Dados de um novo cinema.
     * @return CinemaDTO - Dados salvos do cinema.
     */
    public CinemaDTO save(@NotNull CinemaPayloadDTO newCinema) {
        cinemaRepository.save(newCinema.toEntity(pessoaRepository.findById(newCinema.getIdAdministrador()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"))));
        return cinemaRepository.findByNome(newCinema.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!")).toDTO();
    }

    /**Atualiza cinema existente no banco de dados.
     * @param newCinema CinemaUpdateDTO - Dados de um cinema que será atualizado.
     * @return CinemaDTO - Dados atualizados do cinema.
     */
    public CinemaDTO update(@NotNull CinemaDTO newCinema) {
        CinemaEntity cinemaEntity = cinemaRepository.findById(newCinema.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
        if(newCinema.getNome() != null) cinemaEntity.setNome(newCinema.getNome());
        if(newCinema.getCaixa() != null) cinemaEntity.setCaixa(newCinema.getCaixa());
        cinemaRepository.save(cinemaEntity);
        return cinemaEntity.toDTO();
    }

    /**Deleta cinema do banco de dados.
     * @param id Long - Identificador de um cinema existente.
     */
    public void delete(@NotNull Long id) {cinemaRepository.deleteById(id);}
}
