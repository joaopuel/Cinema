package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.FilmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
}
