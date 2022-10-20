package com.entra21.grupo1.model.entity;

import com.entra21.grupo1.model.dto.CadeiraDTO;
import com.entra21.grupo1.model.dto.CadeiraDTOWithDetails;
import com.entra21.grupo1.view.repository.IngressoRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cadeira")
public class CadeiraEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id")
    @JsonIgnore
    private SalaEntity sala;

    @Column(name = "tipo_cadeira")
    private String tipoCadeira;

    @Column(name = "fileira")
    private Integer fileira;

    @Column(name = "ordem_fileira")
    private Integer ordemFileira;

    public CadeiraDTO toDTO() {
        CadeiraDTO cadeiraDTO = new CadeiraDTO();
        cadeiraDTO.setId(this.getId());
        cadeiraDTO.setCodigo(this.getCodigo());
        cadeiraDTO.setTipoCadeira(this.getTipoCadeira());
        cadeiraDTO.setFileira(this.getFileira());
        cadeiraDTO.setOrdemFileira(this.getOrdemFileira());
        return cadeiraDTO;
    }

    public CadeiraDTOWithDetails toDTOWithDetails() {
        CadeiraDTOWithDetails cadeiraDTO = new CadeiraDTOWithDetails();
        cadeiraDTO.setId(this.getId());
        cadeiraDTO.setCodigo(this.getCodigo());
        cadeiraDTO.setTipoCadeira(this.getTipoCadeira());
        cadeiraDTO.setFileira(this.getFileira());
        cadeiraDTO.setOrdemFileira(this.getOrdemFileira());
        return cadeiraDTO;
    }
}
