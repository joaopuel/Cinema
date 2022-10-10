package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
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
    private PessoaService pessoaService;

    @Autowired
    private RegistroCaixaService registroCaixaService;

    /**Busca todos os cinemas do banco de dados.
     * @return Lista de todos os cinemas existentes.
     */
    public List<CinemaDTO> getAll() {
        return cinemaRepository.findAll().stream().map(CinemaEntity::toDTO).collect(Collectors.toList());
    }

    /**Busca as informações de um cinema no banco de dados caso pertença ao usuário que está acessando o método.
     * @param idCinema Número de identificação do cinema.
     * @return Dados do cinema.
     */
    public CinemaDTOWithDetails getById(@NotNull Long idCinema) {
        return getCinemaEntity(idCinema).toDTOWithDetails();
    }

    /**Adiciona um cinema ao banco de dados.
     * @param newCinema Dados de um novo cinema.
     * @return  Dados salvos do novo cinema.
     */
    public CinemaDTO save(@NotNull CinemaPayloadDTO newCinema) {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullField(newCinema);
        cinemaRepository.findByNome(newCinema.getNome()).ifPresentOrElse(
                (cinemaEntity) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Este cinema já existe!");
                },
                () -> {
                    cinemaRepository.save(newCinema.toEntity(pessoaService.getUser()));
                });
        return cinemaRepository.findByNome(newCinema.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!")).toDTO();
    }

    /**Atualiza as informações de um cinema no banco de dados.
     * @param newCinema Dados do cinema que devem ser atualizados.
     */
    public void update(@NotNull CinemaDTO newCinema) throws NoSuchFieldException {
        pessoaService.checkNullId(newCinema);
        cinemaRepository.findByNome(newCinema.getNome()).ifPresentOrElse(
                (c) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Este cinema já existe!");
                },
                () -> {
                    CinemaEntity cinemaEntity = getCinemaEntity(newCinema.getId());
                    if (newCinema.getNome() != null) cinemaEntity.setNome(newCinema.getNome());
                    if (newCinema.getLogradouro() != null) cinemaEntity.setLogradouro(newCinema.getLogradouro());
                    if (newCinema.getNumero() != null) cinemaEntity.setNumero(newCinema.getNumero());
                    cinemaRepository.save(cinemaEntity);
                });
    }

    /**Deleta um cinema do banco de dados.
     * @param idCinema Número de identificação do cinema.
     */
    public void delete(@NotNull Long idCinema) {
        cinemaRepository.delete(getCinemaEntity(idCinema));
    }

    public CinemaEntity getCinemaEntity(@NotNull Long idCinema){
        pessoaService.userIsAnAdministrador();
        return cinemaRepository.findById(idCinema).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
    }
}
