package com.entra21.grupo1.model.dto;
import com.entra21.grupo1.model.entity.CadeiraEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import lombok.Data;

@Data
public class CadeiraDTO {
    private Long id;
    private String codigo;
    private SalaDTO sala;
    private String tipoCadeira;
    private Integer fileira;
    private Integer ordemFileira;

    public CadeiraEntity toEntity(SalaEntity sala){
        CadeiraEntity cadeiraEntity = new CadeiraEntity();
        cadeiraEntity.setCodigo(this.getCodigo());
        cadeiraEntity.setFileira(this.getFileira());
        cadeiraEntity.setOrdemFileira(this.getOrdemFileira());
        cadeiraEntity.setTipoCadeira(this.getTipoCadeira());
        cadeiraEntity.setSala(sala);
        return cadeiraEntity;
    }
}
