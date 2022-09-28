package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CadeiraEntity;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.model.entity.SessaoEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IngressoPayloadDTO {
    private Long idSessao;
    private Long idPessoa;
    private Long idCadeira;
    private LocalDateTime dataCompra;

    public IngressoEntity toEntity(SessaoEntity sessaoEntity, PessoaEntity pessoaEntity, CadeiraEntity cadeiraEntity) {
        IngressoEntity ingressoEntity = new IngressoEntity();
        ingressoEntity.setSessao(sessaoEntity);
        ingressoEntity.setPessoa(pessoaEntity);
        ingressoEntity.setCadeira(cadeiraEntity);
        ingressoEntity.setDataCompra(this.getDataCompra());
        return ingressoEntity;
    }
}
