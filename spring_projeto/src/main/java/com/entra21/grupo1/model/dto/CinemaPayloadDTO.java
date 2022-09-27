package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class CinemaPayloadDTO {
    private String nome;
    private Long idAdministrador;
    private Double caixa;

    public CinemaEntity toEntity(PessoaEntity administrador){
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setNome(this.getNome());
        cinemaEntity.setAdministrador(administrador);
        cinemaEntity.setCaixa(this.getCaixa());
        return cinemaEntity;
    }
}
