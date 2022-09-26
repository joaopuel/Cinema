package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.GeneroDTO;
import com.entra21.grupo1.model.dto.GeneroPayloadDTO;
import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.dto.SalaPayloadDTO;
import com.entra21.grupo1.view.service.GeneroService;
import com.entra21.grupo1.view.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/generos")
public class GeneroRestController {

    @Autowired
    private GeneroService generoService;

    //Chama o método getAll do GeneroService
    @GetMapping
    public List<GeneroDTO> getGeneros() {
        return generoService.getAll();
    }

    //Chama o método saveGenero do GeneroService
    @PostMapping
    public void addGenero(@RequestBody GeneroPayloadDTO newGenero) {
        generoService.saveGenero(newGenero);
    }

    //Chama o método update do GeneroService
    @PutMapping
    public GeneroDTO updateGenero(@RequestBody GeneroDTO newGenero) {
        return generoService.update(newGenero);
    }

    //Chama o método delete do GeneroService
    @DeleteMapping
    public void deletePessoa(@RequestParam(name = "id") Long id) {generoService.delete(id);}
}
