package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.model.entity.PessoaEntity;
import com.entra21.grupo1.view.service.CinemaService;
import org.hibernate.collection.internal.PersistentSortedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaRestController {
    @Autowired
    private CinemaService cinemaService;

    /**
     * Chama o método getAll de CinemaService
     * @return List<CinemaDTO> contendo todos os cinemas
     */
    @GetMapping
    public List<CinemaDTO> getCinemas() {
        return cinemaService.getAll();
    }

    @GetMapping("/{id}")
    public CinemaDTOWithDetails getCinemaById(@AuthenticationPrincipal PessoaEntity user, @PathVariable(name = "id") Long id){
        if(user.isAdministrador()) {
            return cinemaService.getById(user.getId(), id);
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Apenas para adiministradores de cinema!");
        }
    }

    /**
     * Chama o método save de CinemaService
     * @param newCinema no formato CinemaPayloadDTO
     * @return objeto do tipo CinemaDTO que foi salvo
     */
    @PostMapping
    public CinemaDTO addCinema(@AuthenticationPrincipal PessoaEntity user, @RequestBody CinemaPayloadDTO newCinema) {
        if(user.isAdministrador()) {
            return cinemaService.save(user, newCinema);
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Chama o método update de CinemaService
     * @param newCinema no formato CinemaPayloadDTO
     * @return retorna um objeto CinemaDTO com todas as informaçoes finais.
     */
    @PutMapping
    public CinemaDTO updateCinema(@AuthenticationPrincipal PessoaEntity user, @RequestBody CinemaDTO newCinema) {
        if(user.isAdministrador()) {
            return cinemaService.update(user.getId(), newCinema);
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Chama o método delete de CinemaService
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deletePessoa(@AuthenticationPrincipal PessoaEntity user, @PathVariable(name = "id") Long id) {
        if(user.isAdministrador()) {
            cinemaService.delete(id);
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }
}
