package com.entra21.grupo1.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cinema")
public class CinemaEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_administrador", referencedColumnName = "id")
    private PessoaEntity administrador;

    @Column(name = "caixa")
    private Double caixa;
}
