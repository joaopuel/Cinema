package com.entra21.grupo1.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cadeira")
public class CadeiraEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id")
    private SalaEntity sala;

    @Column(name = "tipo_cadeira")
    private String tipoCadeira;

    @Column(name = "fileira")
    private Integer fileira;

    @Column(name = "ordem_fileira")
    private Integer ordemFileira;
}
