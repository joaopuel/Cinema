package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {

    @Query(value = "select s.* from sessao s join sala s2 on s.id_sala = s2.id where s2.id = :idSala and s.data_sessao  = :data", nativeQuery = true)
    SessaoEntity findByDataBySala(@Param("idSala") Long idSala, @Param("data") LocalDateTime data);

    @Query(value = "select x.* from sessao x join sala y on x.id_sala = y.id where x.data_sessao = (select max(s2.data_sessao) from (select s.* from sessao s join sala sl on s.id_sala = sl.id where sl.id = :idSala and s.data_sessao < :data) s2) and y.id = :idSala", nativeQuery = true)
    SessaoEntity findFirstBeforeBySala(@Param("idSala") Long idSala, @Param("data") LocalDateTime data);

    @Query(value = "select x.* from sessao x join sala y on x.id_sala = y.id where x.data_sessao = (select min(s2.data_sessao) from (select s.* from sessao s join sala sl on s.id_sala = sl.id where sl.id = :idSala and s.data_sessao > :data) s2) and y.id = :idSala", nativeQuery = true)
    SessaoEntity findFirstAfterBySala(@Param("idSala") Long idSala, @Param("data") LocalDateTime data);

    @Query(value = "select s.* from sessao s join sala sl on s.id_sala = sl.id where sl.id = :idSala and s.data_sessao BETWEEN :deData AND :ateData", nativeQuery = true)
    List<SessaoEntity> findSessoesEntreDataBySala(@Param("idSala") Long idSala, @Param("deData") LocalDateTime deData, @Param("ateData") LocalDateTime ateData);
}
