package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.AvaliacaoEntity;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AvaliacaoDTO {
    private Long id;
    private Double rating;
    private String comentario;
    private LocalDate dataAvaliacao;
    private String nomeUsuario;
    private String sobrenomeUsuario;
}
