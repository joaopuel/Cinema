package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.FilmeDTO;
import com.entra21.grupo1.model.dto.FilmeDetailsDTO;
import com.entra21.grupo1.model.dto.FilmePayLoadDTO;
import com.entra21.grupo1.view.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping()
    public List<FilmeDTO> getAllFilmes(@RequestParam(name = "genero", required = false) String genero, @RequestParam(name = "nota", required = false) Double nota) {
        return filmeService.getAll(genero, nota);
    }

    @GetMapping("/{nome}")
    public FilmeDetailsDTO getFilmeByNome(@PathVariable(name = "nome") String nome){
        return filmeService.getByNome(nome);
    }

    @PostMapping
    public FilmeDTO addFilme(@RequestBody FilmePayLoadDTO newfilme){
        return filmeService.saveFilme(newfilme);
    }
}
