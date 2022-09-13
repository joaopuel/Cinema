package com.entra21.grupo1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sessao")
public class SessaoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_sessao")
    private LocalDateTime dataSessao;

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id")
    private SalaEntity sala;

    @ManyToOne
    @JoinColumn(name = "id_filme", referencedColumnName = "id")
    private FilmeEntity filme;

    @Column(name = "valor_inteira")
    private String valorInteira;

    @Column(name = "valor_meia")
    private String valorMeia;
}
