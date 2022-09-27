package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
    Optional <CinemaEntity> findByNome(String nome);
}
