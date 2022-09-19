package com.entra21.grupo1.model.dto;

import com.entra21.grupo1.model.entity.CinemaEntity;
import com.entra21.grupo1.model.entity.SalaEntity;
import lombok.Data;

import java.util.List;

@Data
public class CinemaDTO {
    private Long id;
    private String nome;
    private PessoaDTO administrador;
    private List<SalaDTO> salas;
    private Double caixa;

    public CinemaDTO toDTO() {
        CinemaDTO cinemaDTO = new CinemaDTO();

        entity.setStudentId(this.studentId);
        entity.setFirstName(this.firstName);
        entity.setLastName(this.lastName);
        entity.setYear(this.year);

        return cinemaDTO;
    }

}