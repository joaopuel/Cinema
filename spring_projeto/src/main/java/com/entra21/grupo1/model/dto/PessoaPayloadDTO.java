package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.PessoaEntity;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.jar.Attributes;

@Data
public class PessoaPayloadDTO {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;

    public PessoaEntity toEntity(){
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setNome(this.getNome());
        pessoaEntity.setSobrenome(this.getSobrenome());
        pessoaEntity.setTelefone(this.getTelefone());
        pessoaEntity.setCpf(this.getCpf());
        pessoaEntity.setSaldoCarteira(0.0);
        pessoaEntity.setLogin(this.getLogin());
        pessoaEntity.setSenha(this.getSenha());
        return pessoaEntity;
    }
}
