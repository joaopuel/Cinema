package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.GeneroEntity;
import lombok.Data;

@Data
public class GeneroPayloadDTO {
    private String nome;

    public GeneroEntity toEntity(){
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setNome(this.getNome());
        return generoEntity;
    }
}
