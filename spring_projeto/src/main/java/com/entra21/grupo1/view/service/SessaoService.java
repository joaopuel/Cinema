package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.dto.SessaoPayloadDTO;
import com.entra21.grupo1.model.dto.SessaoDTOWithDetails;
import com.entra21.grupo1.model.entity.SalaEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private PessoaService pessoaService;

    @Autowired
    private SalaService salaService;

    /**Método retornará todas as sessões de um dia, caso receba uma data. Caso não receba nenhum parâmetro, retornará todas as sessões.
     * @return List<SessaoDTO> - Retorna uma lista de DTO de todas as sessões.
     */
    public List<SessaoDTO> getAll(){
        return sessaoRepository.findAll().stream().map(SessaoEntity::toDTO).collect(Collectors.toList());
    }

    /**Busca sessões pelo seu id com mais detalhes da sala e cadeiras.
     * @param id Long - identificador de uma sessão existente.
     * @return SessaoDTOWithDetails - Dados de uma sessão com mais detalhes.
     */
    public SessaoDTOWithDetails getById(@NotNull Long id) {
        SessaoEntity sessaoEntity = sessaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!"));
        return sessaoEntity.toDTOWithDetails(
                salaService.getSalaDTOWithDetails(sessaoEntity.getSala().getId(), sessaoEntity.getId())
        );
    }

    /**Adiciona novas sessões ao banco de dados.
     * @param newSessao SessaoPayloadDTO - Dados de uma nova sessão.
     */
    public void saveSessao(@NotNull SessaoPayloadDTO newSessao) {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullField(newSessao);

        SessaoEntity sessaoEntity = newSessao.toEntity(
                filmeRepository.findById(newSessao.getIdFilme()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado!")),
                salaRepository.findById(newSessao.getIdSala()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"))
        );

        List<SessaoDTO> sessoes = alreadyExist(sessaoEntity);

        if(sessoes.isEmpty()){
            sessaoRepository.save(sessaoEntity);
        } else {
            String msgSessoes = "";
            for(SessaoDTO s : sessoes){
                msgSessoes = msgSessoes +
                        "{id: " + s.getId().toString() + ", " +
                        "filme: " + s.getNomeFilme() + ", " +
                        "dataSessao: " + s.getDataSessao().toString() + "}, ";
            }
            throw new ResponseStatusException(HttpStatus.CONFLICT, msgSessoes);
        }
    }

    /**Atualiza sessões já existentes no banco de dados.
     * @param newSessao SessaoDTO - Dados de uma sessão que será atualizada.
     * @return SessaoDTO - Dados atualizados da sessão.
     */
    public void update(@NotNull SessaoDTO newSessao) throws NoSuchFieldException {
        pessoaService.checkNullId(newSessao);
        List<SessaoDTO> sessoes = new ArrayList<>();
        SessaoEntity sessaoEntity = getSessaoEntity(newSessao.getId());
        if(newSessao.getValorInteira() != null){ sessaoEntity.setValorInteira(newSessao.getValorInteira());}
        if(newSessao.getTaxaVip() != null){ sessaoEntity.setTaxaVip(newSessao.getTaxaVip());}
        if(newSessao.getTipoSessao() != null){ sessaoEntity.setTipoSessao(newSessao.getTipoSessao());}
        if(newSessao.getDataSessao() != null){
            sessaoEntity.setDataSessao(newSessao.getDataSessao());
            sessoes = alreadyExist(sessaoEntity);
        }

        if(sessoes.isEmpty()){
            sessaoRepository.save(sessaoEntity);
        } else {
            String msgSessoes = "";
            for(SessaoDTO s : sessoes){
                msgSessoes = msgSessoes +
                        "{id: " + s.getId().toString() + ", " +
                        "filme: " + s.getNomeFilme() + ", " +
                        "dataSessao: " + s.getDataSessao().toString() + "}, ";
            }
            throw new ResponseStatusException(HttpStatus.CONFLICT, msgSessoes);
        }
    }

    /**Deleta sessões do banco de dados.
     * @param id Long - identificador de uma sessão existente.
     */
    public void delete(@NotNull Long id) {
        sessaoRepository.delete(getSessaoEntity(id));
    }

    public SessaoEntity getSessaoEntity(Long idSessao){
        pessoaService.userIsAnAdministrador();
        return sessaoRepository.findById(idSessao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!"));
    }

    public List<SessaoDTO> alreadyExist(SessaoEntity newSessao){
        List<SessaoDTO> sessoes = new ArrayList<>();

        SessaoEntity sessaoAnterior = sessaoRepository.findFirstBeforeBySala(newSessao.getSala().getId(), newSessao.getDataSessao());
        if(sessaoAnterior != null && !Objects.equals(sessaoAnterior.getId(), newSessao.getId()) &&
                newSessao.getDataSessao().isBefore(sessaoAnterior.getDataSessao()
                        .plusHours(sessaoAnterior.getFilme().getDuracao().getHour())
                        .plusMinutes(sessaoAnterior.getFilme().getDuracao().getMinute())
                        .plusSeconds(sessaoAnterior.getFilme().getDuracao().getSecond())
                        .plusMinutes(30)
            )){
            sessoes.add(sessaoAnterior.toDTO());
        }

        sessoes.addAll(
                sessaoRepository.findSessoesEntreDataBySala(
                newSessao.getSala().getId(),
                newSessao.getDataSessao(),
                newSessao.getDataSessao()
                        .plusHours(newSessao.getFilme().getDuracao().getHour())
                        .plusMinutes(newSessao.getFilme().getDuracao().getMinute())
                        .plusSeconds(newSessao.getFilme().getDuracao().getSecond())
                        .plusMinutes(30)).stream().map((s) -> {
                            if(!Objects.equals(s.getId(), newSessao.getId())){
                                return s.toDTO();
                            } else {
                                return null;
                            }
        }).filter(Objects::nonNull).collect(Collectors.toList())
        );
        return sessoes;
    }
}
