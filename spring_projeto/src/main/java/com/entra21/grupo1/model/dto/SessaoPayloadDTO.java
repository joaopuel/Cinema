package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import com.entra21.grupo1.model.entity.SessaoEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoPayloadDTO {
    private LocalDateTime dataSessao;
    private Long idFilme;
    private Long idSala;
    private Double valorInteira;
    private Double taxaVip;
    private String tipoSessao;

    public SessaoEntity toEntity(FilmeEntity filmeEntity, SalaEntity salaEntity){
        SessaoEntity sessaoEntity = new SessaoEntity();
        sessaoEntity.setDataSessao(this.getDataSessao());
        sessaoEntity.setValorInteira(this.getValorInteira());
        sessaoEntity.setTaxaVip(this.getTaxaVip());
        sessaoEntity.setTipoSessao(this.getTipoSessao());
        sessaoEntity.setFilme(filmeEntity);
        sessaoEntity.setSala(salaEntity);
        return sessaoEntity;
    }
}
