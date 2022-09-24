package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.model.entity.SessaoEntity;
import lombok.Data;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@Data
public class SessaoPayLoadDTO {
    private LocalDateTime dataSessao;
    private Long idFilme;
    private Long idSala;
    private String valorInteira;
    private String valorMeia;
    private String tipoSessao;

    public SessaoEntity toEntity(FilmeEntity filmeEntity, SalaEntity salaEntity){
        SessaoEntity sessaoEntity = new SessaoEntity();
        sessaoEntity.setDataSessao(this.getDataSessao());
        sessaoEntity.setValorInteira(this.getValorInteira());
        sessaoEntity.setValorMeia(this.getValorMeia());
        sessaoEntity.setTipoSessao(this.getTipoSessao());
        sessaoEntity.setFilme(filmeEntity);
        sessaoEntity.setSala(salaEntity);
        return sessaoEntity;
    }
}
