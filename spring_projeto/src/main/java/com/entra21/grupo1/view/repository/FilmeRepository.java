package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.FilmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
//    public List<FilmeEntity> findAllBySessao_Data_sessao(LocalDateTime dataSessao);
}
