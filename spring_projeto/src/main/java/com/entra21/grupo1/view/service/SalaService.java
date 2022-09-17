package com.entra21.grupo1.view.service;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.view.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

}
