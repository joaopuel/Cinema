package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.RegistroCaixaDTO;
import com.entra21.grupo1.model.dto.RegistroCaixaPayloadDTO;
import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.model.entity.RegistroCaixaEntity;
import com.entra21.grupo1.view.repository.CinemaRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import com.entra21.grupo1.view.repository.RegistroCaixaRepository;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RegistroCaixaService {

    @Autowired
    private RegistroCaixaRepository registroCaixaRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    public List<RegistroCaixaDTO> getAll() {
        pessoaService.userIsAnAdministrador();
        return registroCaixaRepository.findAll().stream().map(RegistroCaixaEntity::toDTO).collect(Collectors.toList());
    }

    public void addRegistro(RegistroCaixaPayloadDTO registro){
        checkNullField(registro);
        pessoaService.userIsAnAdministrador();
        PessoaEntity administrador = pessoaService.getUser();
        administrador.setSaldoCarteira(administrador.getSaldoCarteira()+registro.getValor());
        CinemaEntity cinema = cinemaRepository.findById(registro.getIdCinema()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema não encontrado!"));
        cinema.setCaixa(cinema.getCaixa() + registro.getValor());
        pessoaRepository.save(administrador);
        cinemaRepository.save(cinema);
        registroCaixaRepository.save(registro.toEntity(cinema));
    }

    public void checkNullField(RegistroCaixaPayloadDTO registro)  {
        for (Field f : registro.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if(!(f.getName().equals("operacao") || f.getName().equals("descricao")) && Objects.isNull(f.get(registro))){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Há um campo obrigatório nulo!");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
