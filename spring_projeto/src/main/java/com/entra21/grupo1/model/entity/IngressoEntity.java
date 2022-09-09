package com.entra21.grupo1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ingresso")
public class IngressoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sessao", referencedColumnName = "id")
    private Long idSessao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Long idPessoa;

    @ManyToOne
    @JoinColumn(name = "id_cadeira", referencedColumnName = "id")
    private Long idCadeira;

    @ManyToOne
    @JoinColumn(name = "data_compra", referencedColumnName = "id")
    private LocalDate dataCompra;
}
