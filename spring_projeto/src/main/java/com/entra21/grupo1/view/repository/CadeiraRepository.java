package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.CadeiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadeiraRepository extends JpaRepository<CadeiraEntity, Long> {
}
