package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.CinemaDTOWithDetails;
import com.entra21.grupo1.model.dto.RegistroCaixaDTO;
import com.entra21.grupo1.view.service.CinemaService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "registro_caixa")
public class RegistroCaixaEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operacao")
    private String operacao;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cinema", referencedColumnName = "id")
    @JsonIgnore
    private CinemaEntity cinema;

    @Column(name = "data_operacao")
    private LocalDateTime dataOperacao;

    public RegistroCaixaDTO toDTO(){
        RegistroCaixaDTO registroCaixaDTO = new RegistroCaixaDTO();
        registroCaixaDTO.setId(this.getId());
        registroCaixaDTO.setOperacao(this.getOperacao());
        registroCaixaDTO.setValor(this.getValor());
        registroCaixaDTO.setDescricao(this.getDescricao());
        registroCaixaDTO.setNomeCinema(this.getCinema().getNome());
        registroCaixaDTO.setDataOperacao(this.getDataOperacao());
        return registroCaixaDTO;
    }
}
