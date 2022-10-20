package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaDTOWithDetails;
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
        Integer numFileiras = 0;
        Integer tamFileiras = 0;

        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setId(this.getId());
        salaDTO.setNome(this.getNome());
        if(this.getCadeiras() != null){
            for(CadeiraEntity c : this.getCadeiras()){
                if(c.getFileira() > numFileiras){
                    numFileiras = c.getFileira();
                }
                if(c.getOrdemFileira() > tamFileiras){
                    tamFileiras = c.getOrdemFileira();
                }
            }
            salaDTO.setCadeiras(this.getCadeiras().stream().map(CadeiraEntity::toDTO).collect(Collectors.toList()));
        }
        salaDTO.setNumFileiras(numFileiras);
        salaDTO.setTamFileiras(tamFileiras);
        return salaDTO;
    }

    public SalaDTOWithDetails toDTOWithDetails() {
        Integer numFileiras = 0;
        Integer tamFileiras = 0;

        SalaDTOWithDetails salaDTO = new SalaDTOWithDetails();
        salaDTO.setId(this.getId());
        salaDTO.setNome(this.getNome());
        if(this.getCadeiras() != null){
            for(CadeiraEntity c : this.getCadeiras()){
                if(c.getFileira() > numFileiras){
                    numFileiras = c.getFileira();
                }
                if(c.getOrdemFileira() > tamFileiras){
                    tamFileiras = c.getOrdemFileira();
                }
            }
        }
        salaDTO.setNumFileiras(numFileiras);
        salaDTO.setTamFileiras(tamFileiras);
        return salaDTO;
    }
}
