package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.CadeiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadeiraRepository extends JpaRepository<CadeiraEntity, Long> {

    @Query(value = "select c.* from cadeira c join sala s on c.id_sala = s.id where s.id = :idSala and c.codigo  = :codigo", nativeQuery = true)
    Optional<CadeiraEntity> findByCodigoBySala(@Param("idSala") Long idSala, @Param("codigo") String codigo);

}
