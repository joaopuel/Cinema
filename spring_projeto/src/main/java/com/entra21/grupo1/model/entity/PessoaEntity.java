package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "pessoa")
public class PessoaEntity implements UserDetails {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "saldo_carteira")
    private Double saldoCarteira;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @OneToMany(mappedBy = "administrador")
    private List<CinemaEntity> cinemas;

    public PessoaDTO toPessoaDTO() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(getId());
        pessoaDTO.setNome(getNome());
        pessoaDTO.setSobrenome(getSobrenome());
        pessoaDTO.setTelefone(getTelefone());
        pessoaDTO.setCpf(getCpf());
        pessoaDTO.setSaldoCarteira(getSaldoCarteira());
        pessoaDTO.setLogin(getLogin());
        pessoaDTO.setSenha(getSenha());
        return pessoaDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
