package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.RegistroCaixaEntity;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class RegistroCaixaPayloadDTO {
    private String operacao;
    private Double valor;
    private String descricao;
    private Long idCinema;

    public RegistroCaixaEntity toEntity(CinemaEntity cinema){
        RegistroCaixaEntity registroEntity = new RegistroCaixaEntity();
        registroEntity.setValor(this.getValor());
        registroEntity.setDescricao(this.getDescricao());
        registroEntity.setCinema(cinema);
        registroEntity.setDataOperacao(LocalDateTime.now());
        if(this.getOperacao() == null || !this.getOperacao().equals("Venda")){
            if(this.getValor()>0) registroEntity.setOperacao("Depósito");
            else registroEntity.setOperacao("Retirada");
        }else {
            registroEntity.setOperacao(this.getOperacao());
        }
        return registroEntity;
    }
}