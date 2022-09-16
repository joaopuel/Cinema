package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.IngressoDTO;
import com.entra21.grupo1.model.dto.PessoaDTO;
import com.entra21.grupo1.model.entity.IngressoEntity;
import com.entra21.grupo1.view.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngressoService {
    @Autowired
    private IngressoRepository ingressoRepository;

    //todo
}
