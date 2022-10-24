package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.IngressoPayloadDTO;
import com.entra21.grupo1.model.dto.RegistroCaixaPayloadDTO;
import com.entra21.grupo1.model.entity.CadeiraEntity;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.model.entity.SessaoEntity;
import com.entra21.grupo1.view.repository.CadeiraRepository;
import com.entra21.grupo1.view.repository.IngressoRepository;
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
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private CadeiraRepository cadeiraRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private RegistroCaixaService registroCaixaService;

    /**Busca todos os ingressos do banco de dados.
     * @return List<IngressoDTO> - Retorna uma lista de DTO de todos os ingressos existentes.
     */
    public List<IngressoDTO> getAll() {
        pessoaService.userIsAnAdministrador();
        return ingressoRepository.findAll().stream().map(IngressoEntity::toDTO).collect(Collectors.toList());
    }

    /**Adiciona ingresso ao banco de dados, vinculando à uma pessoa, uma sessão, e uma cadeira.
     * @param newIngresso IngressoPayloadDTO - Dados de um novo ingresso.
     */
    public void saveIngresso(@NotNull IngressoPayloadDTO newIngresso) {
        if(!(pessoaService.getUser().getLogin().equalsIgnoreCase("admin"))){
            pessoaService.checkNullField(newIngresso);
            SessaoEntity sessao = sessaoRepository.findById(newIngresso.getIdSessao()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!"));
            CadeiraEntity cadeira = cadeiraRepository.findById(newIngresso.getIdCadeira()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadeira não encontrada!"));
            IngressoEntity ingresso = newIngresso.toEntity(sessao, pessoaService.getUser(), cadeira);
            IngressoDTO ingressoDTO = ingresso.toDTO();
            if(pessoaService.getUser().getSaldoCarteira() >= ingressoDTO.getValorIngresso()){
                if(cadeira.getSala() == sessao.getSala()){
                    ingressoRepository.findByCadeiraBySessao(cadeira.getId(), sessao.getId()).ifPresentOrElse(
                            (i) -> {
                                throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta cadeira já está ocupada!");
                            }, () -> {
                                ingressoRepository.save(ingresso);
                                RegistroCaixaPayloadDTO registro = new RegistroCaixaPayloadDTO();
                                registro.setOperacao("Venda");
                                registro.setValor(ingressoDTO.getValorIngresso());
                                registro.setDescricao("Ingresso: " + sessao.getFilme().getNome());
                                registro.setIdCinema(cadeira.getSala().getCinema().getId());
                                registroCaixaService.addRegistro(registro);
                                pessoaService.movimentacao(-(ingressoDTO.getValorIngresso()));
                            }
                    );
//                    return ingressoRepository.findByCadeiraBySessao(cadeira.getId(), sessao.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado!")).toDTO();
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadeira e sessão são de salas diferentes!");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Saldo na carteira é insuficiente!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas para usuários!");
        }
    }

    /**Deleta ingresso do banco de dados.
     * @param id Long - Identificador de um ingresso existente.
     */
    public void delete(@NotNull Long id) {
        IngressoEntity ingresso = ingressoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso não encontrado!"));
        if(pessoaService.getUser().getIngressos().stream().anyMatch((i) -> i == ingresso) && ingresso.getSessao().getDataSessao().plusHours(24).isBefore(LocalDateTime.now())){
            ingressoRepository.delete(ingresso);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode excluir esse ingresso!");
        }
    }
}
