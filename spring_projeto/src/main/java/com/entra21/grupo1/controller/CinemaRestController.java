package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.CinemaDTO;
import com.entra21.grupo1.model.dto.CinemaPayloadDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.dto.PessoaPayloadDTO;
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
    public void addCinema(@RequestBody CinemaPayloadDTO newCinema) {
        cinemaService.save(newCinema);
    }

    @PutMapping
    public CinemaDTO updateCinema(@RequestBody CinemaDTO cinema) {
        return cinemaService.update(cinema);
    }
}
