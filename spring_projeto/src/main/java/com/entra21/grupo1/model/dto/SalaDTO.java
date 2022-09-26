package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.SalaEntity;
import lombok.Data;

import java.util.List;

@Data
public class SalaDTO {
    private Long id;
    private String nome;
    private List<CadeiraDTO> cadeiras;
    public SalaEntity toEntity(){
        SalaEntity salaEntity = new SalaEntity();
        salaEntity.setId(this.getId());
        salaEntity.setNome(this.getNome());
        return salaEntity;
    }
}