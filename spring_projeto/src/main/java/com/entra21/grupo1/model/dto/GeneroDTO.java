package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.GeneroEntity;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class GeneroDTO {
    private Long id;
    private String nome;
}
