package com.entra21.grupo1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "avaliacao")
public class AvaliacaoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "data_avaliacao")
    private LocalDate dataAvaliacao;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_filme")
    private Long IdFilme;
}
