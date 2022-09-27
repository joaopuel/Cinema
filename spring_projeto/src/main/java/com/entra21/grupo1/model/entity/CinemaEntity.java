package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @JsonIgnore
    private PessoaEntity administrador;

    @OneToMany(mappedBy = "cinema")
    @EqualsAndHashCode.Exclude
    private Set<SalaEntity> salas;

    @Column(name = "caixa")
    private Double caixa;

    public CinemaDTO toDTO(){
        CinemaDTO cinemaDTO = new CinemaDTO();
        cinemaDTO.setId(this.getId());
        cinemaDTO.setNome(this.getNome());
        cinemaDTO.setIdAdministrador(this.getAdministrador().getId());
        if(this.getSalas() != null) {
            cinemaDTO.setSalas(this.getSalas().stream().map(SalaEntity::toDTO).collect(Collectors.toList()));
        }
        cinemaDTO.setCaixa(this.getCaixa());
        return cinemaDTO;
    }
}
