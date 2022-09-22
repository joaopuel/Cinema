package com.entra21.grupo1.model.dto;

import lombok.Data;

@Data
public class CadeiraPayloadDTO {

    private String codigo;
    private String tipoCadeira;
    private Integer fileira;
    private Long idSala;
    private Integer ordemFileira;
}
