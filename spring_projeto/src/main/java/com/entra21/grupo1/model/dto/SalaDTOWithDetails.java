package com.entra21.grupo1.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SalaDTOWithDetails {
    private Long id;
    private String nome;
    private Integer numFileiras;
    private Integer tamFileiras;
    private List<CadeiraDTOWithDetails> cadeiras;
}