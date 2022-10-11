package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.IngressoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngressoRepository extends JpaRepository<IngressoEntity, Long> {
    @Query(value = "select i.* from ingresso i join cadeira c on i.id_cadeira = c.id join sessao s on i.id_sessao = s.id where c.id = :idCadeira and s.id = :idSessao", nativeQuery = true)
    Optional<IngressoEntity> findByCadeiraBySessao(@Param("idCadeira") Long idCadeira, @Param("idSessao") Long idSessao);
}
