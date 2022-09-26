package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class GenerosFilmeDTO {
    private Long idFilme;
    private List<Long> idGeneros;
}