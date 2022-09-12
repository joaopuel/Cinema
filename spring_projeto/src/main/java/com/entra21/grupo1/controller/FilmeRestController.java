package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public List<FilmeEntity> getAllFilmes(){
        return filmeRepository.findAll();
    }

}
