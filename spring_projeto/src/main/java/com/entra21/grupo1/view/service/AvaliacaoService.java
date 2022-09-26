package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.AvaliacaoDTO;
import com.entra21.grupo1.model.dto.AvaliacaoPayloadDTO;
import com.entra21.grupo1.model.entity.AvaliacaoEntity;
import com.entra21.grupo1.view.repository.AvaliacaoRepository;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private FilmeRepository filmeRepository;

    public AvaliacaoPayloadDTO salvarAvaliacao(AvaliacaoPayloadDTO avaliacaoPayloadDTO) {
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity();
        avaliacaoEntity.setUsuario(pessoaRepository.findByName(avaliacaoPayloadDTO.getNomeUsuario()));
        avaliacaoEntity.setFilme(filmeRepository.findByNome(avaliacaoPayloadDTO.getNomeFilme()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!")));
        avaliacaoEntity.setRating(avaliacaoPayloadDTO.getRating());
        avaliacaoEntity.setDataAvaliacao(avaliacaoPayloadDTO.getDataAvaliacao());
        avaliacaoEntity.setComentario(avaliacaoPayloadDTO.getComentario());
        avaliacaoRepository.save(avaliacaoEntity);
        return avaliacaoPayloadDTO;
    }

    public void delete(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
