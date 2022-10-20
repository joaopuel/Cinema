package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.CadeiraDTOWithDetails;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaDTOWithDetails;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.view.repository.CadeiraRepository;
import com.entra21.grupo1.view.repository.IngressoRepository;
import com.entra21.grupo1.view.repository.SalaRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private CadeiraRepository cadeiraRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    public SalaDTO getById(Long idSala) {
        return getSalaEntity(idSala).toDTO();
    }

    /**Adiciona uma nova sala ao banco de dados.
     * @param newSala Dados de uma nova sala.
     * @return Dados da salvos da nova sala.
     */
    public SalaDTO saveSala(@NotNull SalaPayloadDTO newSala) {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullField(newSala);
        salaRepository.findByNomeByCinema(newSala.getIdCinema(), newSala.getNome()).ifPresentOrElse(
                (s) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta sala já existe!");
                },
                () -> {
                    salaRepository.save(newSala.toEntity(cinemaService.getCinemaEntity(newSala.getIdCinema())));
                }
        );
        return salaRepository.findByNomeByCinema(newSala.getIdCinema(), newSala.getNome()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!")).toDTO();
    }

    /**Atualiza as informações de uma sala no banco de dados.
     * @param newSala Dados da sala que devem ser atualizados.
     * @return Dados atualizados da sala.
     */
    public void update(@NotNull SalaDTO newSala) throws NoSuchFieldException {
        pessoaService.checkNullId(newSala);
        SalaEntity salaEntity = getSalaEntity(newSala.getId());
        salaRepository.findByNomeByCinema(salaEntity.getCinema().getId(), newSala.getNome()).ifPresentOrElse(
                (s) -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta sala já existe!");
                },
                () -> {
                    if(newSala.getNome() != null) salaEntity.setNome(newSala.getNome());
                    salaRepository.save(salaEntity);
                }
        );
    }

    /**Deleta uma sala do banco de dados.
     * @param idSala Número de identificação da sala que deve ser deletada.
     */
    public void delete(@NotNull Long idSala) {
        salaRepository.delete(getSalaEntity(idSala));
    }

    public SalaEntity getSalaEntity(@NotNull Long idSala){
        pessoaService.userIsAnAdministrador();
        return salaRepository.findById(idSala).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"));
    }

    public SalaDTOWithDetails getSalaDTOWithDetails(Long idSala, Long idSessao){
        SalaEntity salaEntity = salaRepository.findById(idSala).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada!"));
        SalaDTOWithDetails salaDTO = salaEntity.toDTOWithDetails();
        salaDTO.setCadeiras(getCadeirasDTOWithDetails(idSessao));
        return salaDTO;
    }

    public List<CadeiraDTOWithDetails> getCadeirasDTOWithDetails(Long idSessao){
        return cadeiraRepository.findAll().stream().map( (c) -> {
            CadeiraDTOWithDetails cadeiraDTO;
            cadeiraDTO = c.toDTOWithDetails();
            cadeiraDTO.setOcupado(ingressoRepository.findByCadeiraBySessao(cadeiraDTO.getId(), idSessao).isPresent());
            return cadeiraDTO;
        }).collect(Collectors.toList());
    }
}
