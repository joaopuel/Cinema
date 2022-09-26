package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.IngressoPayLoadDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.view.repository.CadeiraRepository;
import com.entra21.grupo1.view.repository.IngressoRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import com.entra21.grupo1.view.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CadeiraRepository cadeiraRepository;

    public List<IngressoDTO> getAll() {
        return ingressoRepository.findAll().stream().map(IngressoEntity::toDTO).collect(Collectors.toList());
    }

    public void saveIngresso(IngressoPayLoadDTO newIngresso) {
        ingressoRepository.save(newIngresso.toEntity(
                sessaoRepository.findById(newIngresso.getIdSessao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!")),
                pessoaRepository.findById(newIngresso.getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!")),
                cadeiraRepository.findById(newIngresso.getIdCadeira()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadeira não encontrada!"))
        ));
    }

    public void delete(Long id) {
        ingressoRepository.deleteById(id);
    }
}
