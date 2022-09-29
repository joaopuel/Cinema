package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class CinemaPayloadDTO {
    private String nome;
    private String logradouro;
    private Integer numero;

    public CinemaEntity toEntity(PessoaEntity administrador){
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setNome(this.getNome());
        cinemaEntity.setAdministrador(administrador);
        cinemaEntity.setLogradouro(this.getLogradouro());
        cinemaEntity.setNumero(this.getNumero());
        cinemaEntity.setCaixa(0.0);
        return cinemaEntity;
    }
}
