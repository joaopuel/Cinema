package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.PessoaEntity;
import lombok.Data;

@Data
public class PessoaPayloadDTO {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double saldoCarteira;
    private String login;
    private String senha;

    public PessoaEntity toEntity(){
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setNome(this.getNome());
        pessoaEntity.setSobrenome(this.getSobrenome());
        pessoaEntity.setTelefone(this.getTelefone());
        pessoaEntity.setCpf(this.getCpf());
        pessoaEntity.setSaldoCarteira(this.getSaldoCarteira());
        pessoaEntity.setLogin(this.getLogin());
        pessoaEntity.setSenha(this.getSenha());
        return pessoaEntity;
    }
}
