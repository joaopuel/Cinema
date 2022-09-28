package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.*;
import com.entra21.grupo1.view.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Chama o método save de CinemaService
     * @param newCinema no formato CinemaPayloadDTO
     * @return objeto do tipo CinemaDTO que foi salvo
     */
    @PostMapping
    public CinemaDTO addCinema(@RequestBody CinemaPayloadDTO newCinema) {
        return cinemaService.save(newCinema);
    }

    /**
     * Chama o método update de CinemaService
     * @param cinema no formato CinemaPayloadDTO
     * @return retorna um objeto CinemaDTO com todas as informaçoes finais.
     */
    @PutMapping
    public CinemaDTO updateCinema(@RequestBody CinemaDTO newCinema) {
        return cinemaService.update(newCinema);
    }
    
    /**
     * Chama o método delete de CinemaService
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable(name = "id") Long id) {cinemaService.delete(id);}
}
