package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.SessaoDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "filme")
public class FilmeEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "duracao")
    private LocalTime duracao;

    @Column(name = "sinopse")
    private String sinopse;

    @Column(name = "diretor")
    private String diretor;

    @Column(name = "ano_lancamento")
    private String anoLancamento;

    @Column(name = "cartaz")
    private String cartaz;

    @OneToMany
    @JoinColumn()
    private Set<SessaoEntity> sessoes;

//    public List<SessaoDTO> getSessoes(){
//        List<SessaoDTO> list = new ArrayList<>();
//        for(SessaoEntity sessao : this.sessoes){
//            SessaoDTO sessaoDTO = new SessaoDTO();
//            sessaoDTO.setDataSessao(sessao.getDataSessao());
//            list.add(sessaoDTO);
//        }
//        return list;
//    }
}
