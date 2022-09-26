package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "sala")
public class SalaEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_cinema", referencedColumnName = "id")
    @JsonIgnore
    private CinemaEntity cinema;

    @OneToMany(mappedBy = "sala")
    @EqualsAndHashCode.Exclude
    private Set<CadeiraEntity> cadeiras;

    public SalaDTO toDTO() {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setId(this.getId());
        salaDTO.setNome(this.getNome());
        salaDTO.setCadeiras(this.getCadeiras().stream().map(CadeiraEntity::toDTO).collect(Collectors.toList()));
        return salaDTO;
    }
}
