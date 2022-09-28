package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.AvaliacaoDTO;
import com.entra21.grupo1.model.dto.AvaliacaoPayloadDTO;
import com.entra21.grupo1.model.entity.AvaliacaoEntity;
import com.entra21.grupo1.view.repository.AvaliacaoRepository;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.PessoaRepository;
import com.sun.istack.NotNull;
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


    /**Adiciona avaliação de usuário a um filme do banco de dados.
     * @param newAvaliacao AvaliacaoPayloadDTO - Dados de uma nova avaliação.
     */
    public void saveAvaliacao(@NotNull AvaliacaoPayloadDTO newAvaliacao) {
        avaliacaoRepository.save(newAvaliacao.toEntity(
                pessoaRepository.findById(newAvaliacao.getIdUsuario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")),
                filmeRepository.findById(newAvaliacao.getIdFilme()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"))
        ));
    }

    /**Deleta avaliação de um filme no banco de dados.
     * @param id Long - Identificador de uma avaliação existente.
     */
    public void delete(@NotNull Long id) {
        avaliacaoRepository.deleteById(id);
    }

    /**Atualiza avaliação de um filme no banco de dados.
     * @param newAvaliacao AvaliacaoDTO - Dados de uma avaliação que serão atualizados
     * @return AvaliacaoDTO - Dados atualizados da avaliação.
     */
    public AvaliacaoDTO update(@NotNull AvaliacaoDTO newAvaliacao) {
        AvaliacaoEntity avaliacaoEntity = avaliacaoRepository.findById(newAvaliacao.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada!"));
        if(newAvaliacao.getRating() != null){ avaliacaoEntity.setRating(newAvaliacao.getRating());}
        if(newAvaliacao.getComentario() != null){ avaliacaoEntity.setComentario(newAvaliacao.getComentario());}
        avaliacaoEntity.setDataAvaliacao(newAvaliacao.getDataAvaliacao());
        avaliacaoRepository.save(avaliacaoEntity);
        return avaliacaoEntity.toDTO();
    }
}
