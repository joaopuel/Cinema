package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import lombok.Data;

@Data
public class SalaPayloadDTO {
    private String nome;
    private Long idCinema;

    public SalaEntity toEntity(CinemaEntity cinemaEntity){
        SalaEntity salaEntity = new SalaEntity();
        salaEntity.setNome(this.getNome());
        salaEntity.setCinema(cinemaEntity);
        return salaEntity;
    }
}
