package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.AvaliacaoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "avaliacao")
public class AvaliacaoEntity {@Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private Double nota;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "data_avaliacao")
    private LocalDateTime dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private PessoaEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_filme", referencedColumnName = "id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private FilmeEntity filme;

    public AvaliacaoDTO toDTO() {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(this.getId());
        avaliacaoDTO.setNomeUsuario(this.getUsuario().getNome());
        avaliacaoDTO.setSobrenomeUsuario(this.getUsuario().getSobrenome());
        avaliacaoDTO.setDataAvaliacao(this.getDataAvaliacao());
        avaliacaoDTO.setNota(this.getNota());
        avaliacaoDTO.setComentario(this.getComentario());
        return avaliacaoDTO;
    }
}
