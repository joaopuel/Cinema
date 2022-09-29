package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    /**Busca todos os cinemas do banco de dados.
     * @return Lista de todos os cinemas existentes.
     */
    public List<CinemaDTO> getAll() {
        return cinemaRepository.findAll().stream().map(CinemaEntity::toDTO).collect(Collectors.toList());
    }

    /**Busca as informações de um cinema no banco de dados caso pertença ao usuário que está acessando o método.
     * @param idUser Número de identificação do administrador.
     * @param idCinema Número de identificação do cinema.
     * @return Dados do cinema.
     */
    public CinemaDTOWithDetails getById(Long idUser, Long idCinema) {
        return pessoaRepository.findById(idUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"))
                .getCinemas().stream().filter(c -> Objects.equals(c.getId(), idCinema)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!")).toDTOWithDetails();
    }

    /**Adiciona um cinema ao banco de dados.
     * @param newCinema Dados de um novo cinema.
     * @return  Dados salvos do novo cinema.
     */
    public CinemaDTO save(@AuthenticationPrincipal PessoaEntity user, @NotNull CinemaPayloadDTO newCinema) {
        cinemaRepository.findByNome(newCinema.getNome()).ifPresentOrElse(
                (cinemaEntity) -> {throw new ResponseStatusException(HttpStatus.CONFLICT, "Este cinema já existe!");},
                () -> {
                    cinemaRepository.save(newCinema.toEntity(user));
                } );
        return cinemaRepository.findByNome(newCinema.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!")).toDTO();
    }

    /**Atualiza as informações de um cinema no banco de dados.
     * @param newCinema Dados do cinema que devem ser atualizados.
     * @return Dados atualizados do cinema.
     */
    public CinemaDTO update(Long idUser, @NotNull CinemaDTO newCinema) {
        CinemaEntity cinemaEntity = cinemaRepository.findById(newCinema.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
        if(newCinema.getNome() != null) cinemaEntity.setNome(newCinema.getNome());
        if(newCinema.getLogradouro() != null) cinemaEntity.setLogradouro(newCinema.getLogradouro());
        if(newCinema.getNumero() != null) cinemaEntity.setNumero(newCinema.getNumero());
        cinemaRepository.save(cinemaEntity);
        return cinemaEntity.toDTO();
    }

    /**Deleta um cinema do banco de dados.
     * @param id Número de identificação do cinema.
     */
    public void delete(@NotNull Long id) {cinemaRepository.deleteById(id);}
}
