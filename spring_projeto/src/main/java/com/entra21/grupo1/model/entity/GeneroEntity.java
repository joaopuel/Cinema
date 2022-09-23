package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.GeneroDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genero")
public class GeneroEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    public GeneroDTO toDTO(){
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(this.getId());
        generoDTO.setNome(this.getNome());
        return generoDTO;
    }
}
