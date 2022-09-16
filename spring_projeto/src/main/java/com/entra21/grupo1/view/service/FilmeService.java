package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.FilmeDTO;
import com.entra21.grupo1.model.dto.SessaoDTO;
import com.entra21.grupo1.model.entity.FilmeEntity;
import com.entra21.grupo1.view.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<FilmeDTO> getAll(LocalDateTime dataSessao) {
        List<FilmeEntity> list;
        if(dataSessao == null){
            list = filmeRepository.findAll();
        }else {
            list = filmeRepository.findAllFilmesComSessoesEntre(dataSessao.toLocalDate().atStartOfDay(), dataSessao.toLocalDate().plusDays(1).atStartOfDay());
        }

        return list.stream().map( f -> {
            FilmeDTO filmeDTO = new FilmeDTO();
            filmeDTO.setId(f.getId());
            filmeDTO.setNome(f.getNome());
            filmeDTO.setDuracao(f.getDuracao());
            filmeDTO.setSinopse(f.getSinopse());
            filmeDTO.setDiretor(f.getDiretor());
            filmeDTO.setAnoLancamento(f.getAnoLancamento());
            filmeDTO.setCartaz(f.getCartaz());

            filmeDTO.setSessoes(
                    f.getSessoes().stream().map( s -> {
                        SessaoDTO sessaoDTO = new SessaoDTO();
                        sessaoDTO.setId(s.getId());
                        sessaoDTO.setDataSessao(s.getDataSessao());
                        sessaoDTO.setValorMeia(s.getValorMeia());
                        sessaoDTO.setValorInteira(s.getValorInteira());
                        return sessaoDTO;
                    }).collect(Collectors.toList())
            );

            return filmeDTO;
        }).collect(Collectors.toList());
    }
}
