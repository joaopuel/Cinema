package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.dto.SalaDTO;
import com.entra21.grupo1.model.entity.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, Long> {

    @Query(value = "select s.* from sala s join cinema c on s.id_cinema = c.id where c.id = :idCinema and s.nome  = :nome", nativeQuery = true)
    Optional<SalaEntity> findByNomeByCinema(@Param("idCinema") Long idCinema, @Param("nome") String nome);

}
