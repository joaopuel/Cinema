package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.FilmeDTO;
import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.view.service.FilmeService;
import com.entra21.grupo1.view.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoRestController {

    @Autowired
    private SessaoService sessaoService;

    @GetMapping
    public List<SessaoDTO> getAllSessoes(@RequestParam(name = "dataSessao", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataSessao) {
        return sessaoService.getAll(dataSessao);
    }

}
