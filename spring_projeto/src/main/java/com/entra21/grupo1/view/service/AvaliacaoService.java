package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.AvaliacaoDTO;
import com.entra21.grupo1.model.dto.AvaliacaoPayloadDTO;
import com.entra21.grupo1.model.entity.AvaliacaoEntity;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.view.repository.AvaliacaoRepository;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private PessoaService pessoaService;


    /**Adiciona avaliação de usuário a um filme do banco de dados.
     * @param newAvaliacao AvaliacaoPayloadDTO - Dados de uma nova avaliação.
     */
    public void saveAvaliacao(@NotNull AvaliacaoPayloadDTO newAvaliacao) {
        this.checkNullField(newAvaliacao);
        FilmeEntity filme = filmeRepository.findById(newAvaliacao.getIdFilme()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!"));
        if(pessoaService.getUser().getIngressos().stream().anyMatch((i) -> i.getSessao().getFilme() == filme)){
            if(pessoaService.getUser().getAvaliacoes().stream().noneMatch((a) -> a.getFilme() == filme)){
                avaliacaoRepository.save(newAvaliacao.toEntity(
                        pessoaService.getUser(),
                        filme
                ));
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você já avaliou esse filme!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você deve ter um ingresso para avaliar esse filme!");
        }
    }

    /**Atualiza avaliação de um filme no banco de dados.
     * @param newAvaliacao AvaliacaoDTO - Dados de uma avaliação que serão atualizados
     * @return AvaliacaoDTO - Dados atualizados da avaliação.
     */
    public void update(@NotNull AvaliacaoDTO newAvaliacao) throws NoSuchFieldException {
        pessoaService.checkNullId(newAvaliacao);
        AvaliacaoEntity avaliacaoEntity = avaliacaoRepository.findById(newAvaliacao.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada!"));
        if(pessoaService.getUser().getAvaliacoes().stream().anyMatch((a) -> a == avaliacaoEntity)){
            if(newAvaliacao.getNota() != null){ avaliacaoEntity.setNota(newAvaliacao.getNota());}
            if(newAvaliacao.getComentario() != null){ avaliacaoEntity.setComentario(newAvaliacao.getComentario());}
            avaliacaoEntity.setDataAvaliacao(LocalDateTime.now());
            avaliacaoRepository.save(avaliacaoEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o usuário que escreveu esta avaliação pode modificá-la!");
        }
    }

    /**Deleta avaliação de um filme no banco de dados.
     * @param id Long - Identificador de uma avaliação existente.
     */
    public void delete(@NotNull Long id) {
        AvaliacaoEntity avaliacaoEntity = avaliacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada!"));
        if(pessoaService.getUser().getAvaliacoes().stream().anyMatch((a) -> a == avaliacaoEntity)){
            avaliacaoRepository.delete(avaliacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada!")));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o usuário que escreveu esta avaliação pode modificá-la!");
        }
    }

    public void checkNullField(Object object)  {
        for (Field f : object.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!(f.getName().equalsIgnoreCase("comentario")) && Objects.isNull(f.get(object))) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Há um campo obrigatório nulo!");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
