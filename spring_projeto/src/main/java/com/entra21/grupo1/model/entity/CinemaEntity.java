package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "cinema")
    private List<SalaEntity> salas;

    @Column(name = "caixa")
    private Double caixa;
}
