package com.entra21.grupo1.controller;

import com.entra21.grupo1.model.dto.AvaliacaoDTO;
import com.entra21.grupo1.model.dto.AvaliacaoPayloadDTO;
import com.entra21.grupo1.view.service.AvaliacaoService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoRestController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    /**
     * O mètodo adiciona uma avaliação ao banco de dados
     * @param newAvaliacao AvaliacaoPayloadDTO - dados da avaliacao a ser adicionada
     */
    @PostMapping
    public void addAvaliacao(@NotNull @RequestBody AvaliacaoPayloadDTO newAvaliacao) {
        avaliacaoService.saveAvaliacao(newAvaliacao);
    }

    /**
     * O método atualiza as informações de uma avaliação já existente no banco de dados atráves de um objeto.
     * Caso não seja informado algum dos atributos desse objeto, não será alterada a informação respectiva.
     * @param newAvaliacao AvaliacaoDTO - recebe dados que iram ser atualizados
     * @return - AaliacaoDTO - retorna as informacoes da avaliação.
     */
    @PutMapping
    public AvaliacaoDTO updateAvaliacao(@RequestBody AvaliacaoDTO newAvaliacao){
        return avaliacaoService.update(newAvaliacao);
    }

    /**
     * O método deleta uma avaliação do banco de dados através de sua id
     * @param id  da avalição no formato Long
     */
    @DeleteMapping("/{id}")
    public void deleteAvaliacao(@PathVariable(name = "id") Long id) {avaliacaoService.delete(id);}
}
