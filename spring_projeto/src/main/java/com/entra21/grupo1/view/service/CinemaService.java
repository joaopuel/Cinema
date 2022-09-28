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
        return cinemaRepository.findAll().stream().map( cinema -> {
            CinemaDTO dto = new CinemaDTO();

            dto.setId(cinema.getId());
            dto.setNome(cinema.getNome());
            dto.setAdministrador(cinema.getAdministrador().toDTO());
            dto.setCaixa(cinema.getCaixa());
            dto.setSalas(cinema.getSalas().stream().map(salaEntity -> {
                SalaDTO salaDTO = new SalaDTO();
                salaDTO.setId(salaEntity.getId());
                salaDTO.setNome(salaEntity.getNome());
                return salaDTO;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    /**Adiciona cinema ao banco de dados.
     * @param cinemaPayloadDTO CinemaPayloadDTO - Dados de um novo cinema.
     * @return CinemaDTO - Dados salvos do cinema.
     */
    public CinemaDTO save(@NotNull CinemaPayloadDTO cinemaPayloadDTO) {
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setNome(cinemaPayloadDTO.getNome());
        PessoaEntity pessoa = pessoaRepository.findById(cinemaPayloadDTO.getAdministrador()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"));
        cinemaEntity.setAdministrador(pessoa);
        cinemaEntity.setCaixa(cinemaPayloadDTO.getCaixa());
        cinemaRepository.save(cinemaEntity);

        CinemaDTO c = new CinemaDTO();
        c.setId(cinemaRepository.findByNome(cinemaEntity.getNome()).getId());
        c.setNome(cinemaEntity.getNome());
        c.setCaixa(cinemaEntity.getCaixa());
        c.setAdministrador(cinemaEntity.getAdministrador().toDTO());
        return c;
    }

    /**Atualiza cinema existente no banco de dados.
     * @param cinemaUpdateDTO CinemaUpdateDTO - Dados de um cinema que será atualizado.
     * @return CinemaDTO - Dados atualizados do cinema.
     */
    public CinemaDTO update(@NotNull CinemaUpdateDTO cinemaUpdateDTO) {
        CinemaEntity cinemaEntity = cinemaRepository.findById(cinemaUpdateDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
        if(cinemaUpdateDTO.getNome() != null) cinemaEntity.setNome(cinemaUpdateDTO.getNome());
        if(cinemaUpdateDTO.getAdministrador() != null) {
            PessoaEntity pessoa = pessoaRepository.findById(cinemaUpdateDTO.getAdministrador()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
            cinemaEntity.setAdministrador(pessoa);
        }
        if(cinemaUpdateDTO.getCaixa() != null) cinemaEntity.setCaixa(cinemaUpdateDTO.getCaixa());
        cinemaRepository.save(cinemaEntity);

        CinemaDTO c = new CinemaDTO();
        c.setId(cinemaEntity.getId());
        c.setNome(cinemaEntity.getNome());
        c.setAdministrador(cinemaEntity.getAdministrador().toDTO());
        c.setCaixa(cinemaEntity.getCaixa());
        return c;
    }

    /**Deleta cinema do banco de dados.
     * @param id Long - Identificador de um cinema existente.
     */
    public void delete(@NotNull Long id) {cinemaRepository.deleteById(id);}
}
