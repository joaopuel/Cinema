package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.IngressoDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ingresso")
public class IngressoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sessao", referencedColumnName = "id")
    private SessaoEntity sessao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "id_cadeira", referencedColumnName = "id")
    private CadeiraEntity cadeira;

    @Column(name = "data_compra")
    private LocalDateTime dataCompra;

    public IngressoDTO toDTO() {
        IngressoDTO ingressoDTO = new IngressoDTO();
        ingressoDTO.setId(this.getId());
        ingressoDTO.setSessao(this.getSessao().toDTO());
        ingressoDTO.setIdPessoa(this.getPessoa().getId());
        ingressoDTO.setCadeira(this.getCadeira().toDTO());
        ingressoDTO.setDataCompra(this.getDataCompra());
        ingressoDTO.setNomeCinema(this.getCadeira().getSala().getCinema().getNome());
        ingressoDTO.setNomeFilme(this.getSessao().getFilme().getNome());
        return ingressoDTO;
    }
}
