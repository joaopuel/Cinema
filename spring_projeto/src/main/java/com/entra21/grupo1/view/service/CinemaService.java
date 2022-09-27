package com.entra21.grupo1.view.service;
import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
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

    //Busca todos os cinemas do bando de dados
    public List<CinemaDTO> getAll() {
        return cinemaRepository.findAll().stream().map(CinemaEntity::toDTO).collect(Collectors.toList());
    }

    //Adiciona cinemas ao banco de dados
    public CinemaDTO save(CinemaPayloadDTO newCinema) {
        cinemaRepository.save(newCinema.toEntity(pessoaRepository.findById(newCinema.getIdAdministrador()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"))));
        return cinemaRepository.findByNome(newCinema.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!")).toDTO();
    }

    //Atualiza cinemas já existentes no banco de dados
    public CinemaDTO update(CinemaDTO cinemaDTO) {
        CinemaEntity cinemaEntity = cinemaRepository.findById(cinemaDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
        if(cinemaDTO.getNome() != null) cinemaEntity.setNome(cinemaDTO.getNome());
        if(cinemaDTO.getCaixa() != null) cinemaEntity.setCaixa(cinemaDTO.getCaixa());
        cinemaRepository.save(cinemaEntity);
        return cinemaEntity.toDTO();
    }

    //Deleta cinemas do banco de dados
    public void delete(Long id) {cinemaRepository.deleteById(id);}
}
