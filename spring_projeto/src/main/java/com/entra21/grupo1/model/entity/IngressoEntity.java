package com.entra21.grupo1.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "ingresso")
public class IngressoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "id_sessao", referencedColumnName = "id")
    private Set<SessaoEntity> sessoes;

    @ManyToMany
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private Set<PessoaEntity> pessoas;

    @ManyToMany
    @JoinColumn(name = "id_cadeira", referencedColumnName = "id")
    private Set<CadeiraEntity> cadeiras;

    @Column(name = "data_compra")
    private LocalDate dataCompra;
}
