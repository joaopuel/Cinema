package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.FilmeEntity;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class FilmePayloadDTO {
    private String nome;
    private LocalTime duracao;
    private String sinopse;
    private String diretor;
    private String cartaz;

    public FilmeEntity toEntity(){
        FilmeEntity filmeEntity = new FilmeEntity();
        filmeEntity.setNome(this.getNome());
        filmeEntity.setDuracao(this.getDuracao());
        filmeEntity.setSinopse(this.getSinopse());
        filmeEntity.setDiretor(this.getDiretor());
        filmeEntity.setCartaz(this.getCartaz());
        return filmeEntity;
    }
}
