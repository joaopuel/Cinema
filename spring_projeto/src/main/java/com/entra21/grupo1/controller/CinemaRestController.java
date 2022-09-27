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

    @GetMapping
    public List<CinemaDTO> getCinemas() {
        return cinemaService.getAll();
    }

    @PostMapping
    public CinemaDTO addCinema(@RequestBody CinemaPayloadDTO newCinema) {
        return cinemaService.save(newCinema);
    }

    @PutMapping
    public CinemaDTO updateCinema(@RequestBody CinemaDTO newCinema) {
        return cinemaService.update(newCinema);
    }

    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable(name = "id") Long id) {cinemaService.delete(id);}
}
