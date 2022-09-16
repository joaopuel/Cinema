package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {
    public List<SessaoEntity> findAllByDataSessaoBetween(LocalDateTime from, LocalDateTime to);
}
