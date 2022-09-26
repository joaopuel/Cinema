package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.AvaliacaoEntity;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.service.AvaliacaoService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Data
public class AvaliacaoPayloadDTO {
    private Long idFilme;
    private Long idUsuario;
    private Double rating;
    private String comentario;
    private LocalDate dataAvaliacao;

    public AvaliacaoEntity toEntity(PessoaEntity pessoaEntity, FilmeEntity filmeEntity){
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity();
        avaliacaoEntity.setFilme(filmeEntity);
        avaliacaoEntity.setUsuario(pessoaEntity);
        avaliacaoEntity.setRating(this.getRating());
        avaliacaoEntity.setComentario(this.getComentario());
        avaliacaoEntity.setDataAvaliacao(this.getDataAvaliacao());
        return avaliacaoEntity;
    }
}
