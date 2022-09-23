package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.IngressoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngressoRepository extends JpaRepository<IngressoEntity, Long> {
    @Query(value = "SELECT * FROM ingresso WHERE id_pessoa = :id", nativeQuery = true)
    List<IngressoEntity> findMeuIngressos(@Param("id") Long id);
}
