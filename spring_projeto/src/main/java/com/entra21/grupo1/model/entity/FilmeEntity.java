package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.FilmeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Column(name = "cartaz")
    private String cartaz;

    @OneToMany(mappedBy = "filme")
    @EqualsAndHashCode.Exclude
    private Set<SessaoEntity> sessoes;

    @OneToMany(mappedBy = "filme")
    @EqualsAndHashCode.Exclude
    private Set<AvaliacaoEntity> avaliacoes;

    @ManyToMany
    @JoinTable(
            name = "filme_genero",
            joinColumns = @JoinColumn(name = "id_filme"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    @EqualsAndHashCode.Exclude
    private Set<GeneroEntity> generos;

    public Double getMedia(){
        Double soma = 0.0;
        if(!this.avaliacoes.isEmpty()){
            for(AvaliacaoEntity avaliacao : this.avaliacoes){
                soma += avaliacao.getRating();
            }
            return soma/avaliacoes.toArray().length;
        }else {
            return null;
        }
    }

    public FilmeDTO toDTO() {
        FilmeDTO filmeDTO = new FilmeDTO();
        filmeDTO.setId(this.id);
        filmeDTO.setNome(this.nome);
        filmeDTO.setCartaz(this.cartaz);
        return filmeDTO;
    }
}
