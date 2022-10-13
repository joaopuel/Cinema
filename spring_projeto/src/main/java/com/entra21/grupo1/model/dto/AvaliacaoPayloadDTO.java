package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.AvaliacaoEntity;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvaliacaoPayloadDTO {
    private Long idFilme;
    private Double nota;
    private String comentario;

    public AvaliacaoEntity toEntity(PessoaEntity pessoaEntity, FilmeEntity filmeEntity){
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity();
        avaliacaoEntity.setFilme(filmeEntity);
        avaliacaoEntity.setUsuario(pessoaEntity);
        avaliacaoEntity.setNota(this.getNota());
        avaliacaoEntity.setComentario(this.getComentario());
        avaliacaoEntity.setDataAvaliacao(LocalDateTime.now());
        return avaliacaoEntity;
    }
}
