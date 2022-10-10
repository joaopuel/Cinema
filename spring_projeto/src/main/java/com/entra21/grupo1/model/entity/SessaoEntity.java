package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.dto.SessaoDTOWithDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private SalaEntity sala;

    @ManyToOne
    @JoinColumn(name = "id_filme", referencedColumnName = "id")
    @JsonIgnore
    private FilmeEntity filme;

    @Column(name = "valor_inteira")
    private Double valorInteira;

    @Column(name = "taxa_vip")
    private Double taxaVip;

    @Column(name = "tipo_sessao")
    private String tipoSessao;

    public SessaoDTO toDTO(){
        SessaoDTO sessaoDTO = new SessaoDTO();
        sessaoDTO.setId(this.getId());
        sessaoDTO.setNomeFilme(this.getFilme().getNome());
        sessaoDTO.setDataSessao(this.getDataSessao());
        sessaoDTO.setValorInteira(this.getValorInteira());
        sessaoDTO.setTaxaVip(this.getTaxaVip());
        sessaoDTO.setTipoSessao(this.getTipoSessao());
        sessaoDTO.setIdSala(this.getSala().getId());
        sessaoDTO.setNomeSala(this.getSala().getNome());
        return sessaoDTO;
    }

    public SessaoDTOWithDetails toDTOWithDetails(){
        SessaoDTOWithDetails sessaoWithDetailsDTO = new SessaoDTOWithDetails();
        sessaoWithDetailsDTO.setId(this.getId());
        sessaoWithDetailsDTO.setNomeFilme(this.getFilme().getNome());
        sessaoWithDetailsDTO.setDataSessao(this.getDataSessao());
        sessaoWithDetailsDTO.setValorInteira(this.getValorInteira());
        sessaoWithDetailsDTO.setTaxaVip(this.getTaxaVip());
        sessaoWithDetailsDTO.setTipoSessao(this.getTipoSessao());
        sessaoWithDetailsDTO.setSala(this.getSala().toDTO());
        return sessaoWithDetailsDTO;
    }
}
