package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.AvaliacaoEntity;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.model.entity.GeneroEntity;
import com.entra21.grupo1.model.entity.SessaoEntity;
import lombok.Data;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Data
public class FilmePayLoadDTO {
    private String nome;
    private LocalTime duracao;
    private String sinopse;
    private String diretor;
    private String cartaz;
    private Long idGeneros;

    public FilmeEntity toEntity(GeneroEntity generoEntity){
        FilmeEntity filmeEntity = new FilmeEntity();
        filmeEntity.setNome(this.getNome());
        filmeEntity.setDuracao(this.getDuracao());
        filmeEntity.setSinopse(this.getSinopse());
        filmeEntity.setDiretor(this.getDiretor());
        filmeEntity.setCartaz(this.getCartaz());
        return filmeEntity;
    }
}
