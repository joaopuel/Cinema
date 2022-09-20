package com.entra21.grupo1.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

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

    @Column(name = "cartaz")
    private String cartaz;

    @OneToMany(mappedBy = "filme")
    @EqualsAndHashCode.Exclude
    private Set<SessaoEntity> sessoes;

    @OneToMany(mappedBy = "filme")
    @EqualsAndHashCode.Exclude
    private Set<AvaliacaoEntity> avaliacoes;

    @ManyToMany
    @JoinTable(
            name = "filme_genero",
            joinColumns = @JoinColumn(name = "id_filme"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    @EqualsAndHashCode.Exclude
    private Set<GeneroEntity> generos;
}
