package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import lombok.Data;

import java.util.List;

@Data
public class CinemaDTO {
    private Long id;
    private String nome;
    private String logradouro;
    private Integer numero;
}