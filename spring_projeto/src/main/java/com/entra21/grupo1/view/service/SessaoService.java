package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.dto.SessaoPayloadDTO;
import com.entra21.grupo1.model.dto.SessaoDTOWithDetails;
import com.entra21.grupo1.model.entity.SessaoEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import com.entra21.grupo1.view.repository.SalaRepository;
import com.entra21.grupo1.view.repository.SessaoRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private FilmeService filmeService;

    /**Método retornará todas as sessões de um dia, caso receba uma data. Caso não receba nenhum parâmetro, retornará todas as sessões.
     * @param dataSessao LocalDateTime - Dia.
     * @return List<SessaoDTO> - Retorna uma lista de DTO de todas as sessões.
     */
    public List<SessaoDTO> getAll(LocalDateTime dataSessao){
        List<SessaoEntity> list;
        if(dataSessao == null){
            list = sessaoRepository.findAll();
        }else{
            list = sessaoRepository.findAllByDataSessaoBetween(dataSessao.toLocalDate().atStartOfDay(), dataSessao.toLocalDate().plusDays(1).atStartOfDay());
        }
        return list.stream().map(SessaoEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona novas sessões ao banco de dados.
     * @param newSessao SessaoPayloadDTO - Dados de uma nova sessão.
     */
    public void saveSessao(@NotNull SessaoPayloadDTO newSessao) {
        sessaoRepository.save(
                newSessao.toEntity(
                        filmeRepository.findById(newSessao.getIdFilme()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")),
                        salaRepository.findById(newSessao.getIdSala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"))
                )
        );
    }

    /**Atualiza sessões já existentes no banco de dados.
     * @param newSessao SessaoDTO - Dados de uma sessão que será atualizada.
     * @return SessaoDTO - Dados atualizados da sessão.
     */
    public SessaoDTO update(@NotNull SessaoDTO newSessao) {
        SessaoEntity sessaoEntity = sessaoRepository.findById(newSessao.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!"));
        if(newSessao.getDataSessao() != null){ sessaoEntity.setDataSessao(newSessao.getDataSessao());}
        if(newSessao.getValorInteira() != null){ sessaoEntity.setValorInteira(newSessao.getValorInteira());}
        if(newSessao.getValorMeia() != null){ sessaoEntity.setValorMeia(newSessao.getValorMeia());}
        if(newSessao.getTipoSessao() != null){ sessaoEntity.setTipoSessao(newSessao.getTipoSessao());}
        sessaoRepository.save(sessaoEntity);
        return sessaoEntity.toDTO();
    }

    /**Deleta sessões do banco de dados.
     * @param id Long - identificador de uma sessão existente.
     */
    public void delete(@NotNull Long id) {
        sessaoRepository.deleteById(id);
    }

    /**Busca sessões pelo seu id com mais detalhes da sala e cadeiras.
     * @param id Long - identificador de uma sessão existente.
     * @return SessaoDTOWithDetails - Dados de uma sessão com mais detalhes.
     */
    public SessaoDTOWithDetails getById(@NotNull Long id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!")).toDTOWithDetails();
    }
}
