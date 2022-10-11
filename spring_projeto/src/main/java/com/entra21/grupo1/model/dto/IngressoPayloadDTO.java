package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CadeiraEntity;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.model.entity.SessaoEntity;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Data
public class IngressoPayloadDTO {
    private Long idSessao;
    private Long idCadeira;
    private Boolean meiaEntrada;

    public IngressoEntity toEntity(SessaoEntity sessaoEntity, PessoaEntity pessoaEntity, CadeiraEntity cadeiraEntity) {
        IngressoEntity ingressoEntity = new IngressoEntity();
        ingressoEntity.setSessao(sessaoEntity);
        ingressoEntity.setUsuario(pessoaEntity);
        ingressoEntity.setCadeira(cadeiraEntity);
        ingressoEntity.setDataCompra(LocalDateTime.now());
        ingressoEntity.setMeiaEntrada(this.getMeiaEntrada());
        return ingressoEntity;
    }
}
