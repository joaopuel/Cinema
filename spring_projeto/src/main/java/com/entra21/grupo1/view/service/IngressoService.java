package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.IngressoPayloadDTO;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.view.repository.CadeiraRepository;
import com.entra21.grupo1.view.repository.IngressoRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import com.entra21.grupo1.view.repository.SessaoRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    /**Busca todos os ingressos do banco de dados.
     * @return List<IngressoDTO> - Retorna uma lista de DTO de todos os ingressos existentes.
     */
    public List<IngressoDTO> getAll() {
        return ingressoRepository.findAll().stream().map(IngressoEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona ingresso ao banco de dados, vinculando à uma pessoa, uma sessão, e uma cadeira.
     * @param newIngresso IngressoPayloadDTO - Dados de um novo ingresso.
     */
    public void saveIngresso(@NotNull IngressoPayloadDTO newIngresso) {
        ingressoRepository.save(newIngresso.toEntity(
                sessaoRepository.findById(newIngresso.getIdSessao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!")),
                pessoaRepository.findById(newIngresso.getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!")),
                cadeiraRepository.findById(newIngresso.getIdCadeira()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadeira não encontrada!"))
        ));
    }

    /**Deleta ingresso do banco de dados.
     * @param id Long - Identificador de um ingresso existente.
     */
    public void delete(@NotNull Long id) {
        ingressoRepository.deleteById(id);
    }
}
