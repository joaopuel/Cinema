package com.entra21.grupo1.view.service;
import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.CadeiraEntity;
import com.entra21.grupo1.view.repository.CadeiraRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CadeiraService {

    @Autowired
    private CadeiraRepository cadeiraRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private SalaService salaService;

    /**Adiciona cadeira ao banco de dados.
     * @param newCadeira CadeiraPayloadDTO - Dados de uma nova cadeira.
     */
    public void saveCadeira(@NotNull CadeiraPayloadDTO newCadeira) {
        pessoaService.userIsAnAdministrador();
        pessoaService.checkNullField(newCadeira);
        cadeiraRepository.findByCodigoBySala(newCadeira.getIdSala(), newCadeira.getCodigo()).ifPresentOrElse(
                (c) -> {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta cadeira já existe!");
                },
                () -> {
                    cadeiraRepository.save(newCadeira.toEntity(salaService.getSalaEntity(newCadeira.getIdSala())));
                }
        );
    }

    /**Atualiza cadeira existente do banco de dados.
     * @param newCadeira CadeiraDTO - Dados de uma cadeira que será atualizada.
     * @return CadeiraDTO - Dados atualizados da cadeira.
     */
    public void update(@NotNull CadeiraDTO newCadeira) throws NoSuchFieldException {
        pessoaService.checkNullId(newCadeira);
        CadeiraEntity cadeiraEntity = getCadeiraEntity(newCadeira.getId());
        if(newCadeira.getTipoCadeira() != null) cadeiraEntity.setTipoCadeira(newCadeira.getTipoCadeira());
        cadeiraRepository.save(cadeiraEntity);
    }

    /**Deleta cadeira do banco de dados.
     * @param id Long - Identificador de uma cadeira existente.
     */
    public void delete(@NotNull Long id) {
        cadeiraRepository.delete(getCadeiraEntity(id));
    }

    public CadeiraEntity getCadeiraEntity(Long idCadeira){
        pessoaService.userIsAnAdministrador();
        return cadeiraRepository.findById(idCadeira).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadeira não encontrada"));
    }
}
