package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CadeiraEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import lombok.Data;

@Data
public class CadeiraPayloadDTO {
    private Long idSala;
    private String codigo;
    private String tipoCadeira;
    private Integer fileira;
    private Integer ordemFileira;

    public CadeiraEntity toEntity(SalaEntity salaEntity){
        CadeiraEntity cadeiraEntity = new CadeiraEntity();
        cadeiraEntity.setCodigo(this.getCodigo());
        cadeiraEntity.setTipoCadeira(this.getTipoCadeira());
        cadeiraEntity.setFileira(this.getFileira());
        cadeiraEntity.setOrdemFileira(this.getOrdemFileira());
        cadeiraEntity.setSala(salaEntity);
        return cadeiraEntity;
    }
}
