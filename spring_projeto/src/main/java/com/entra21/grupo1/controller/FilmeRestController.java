package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.FilmeDTO;
import com.entra21.grupo1.model.dto.FilmeDetailsDTO;
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

    @GetMapping
    public List<FilmeDTO> getAllFilmes(@RequestParam(name = "genero", required = false) String genero) {
        return filmeService.getAll(genero);
    }

    @GetMapping("/{nome}")
    public FilmeDetailsDTO getFilmeByNome(@PathVariable(name = "nome") String nome, @RequestParam(name = "dataSessao", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSessao){
        return filmeService.getByNome(nome, dataSessao);
    }

}
