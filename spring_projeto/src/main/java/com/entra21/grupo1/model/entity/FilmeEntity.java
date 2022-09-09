package com.entra21.grupo1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "filme")
public class FilmeEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "duracao")
    private LocalTime duracao;

    @Column(name = "sinopse")
    private String sinopse;

    @Column(name = "diretor")
    private String diretor;

    @Column(name = "ano_lancamento")
    private String anoLancamento;
}
