package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.FilmeEntity;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {

    public List<FilmeEntity> findAllBySessoes_Id(Long dataSessao);

    @Query( value = "select distinct f.* from filme f join sessao s on s.id_filme = f.id where s.data_sessao >= :data", nativeQuery = true)
    List<FilmeEntity> findAllFilmesComSessoesDepois(@Param("data") LocalDateTime data);

    @Query( value = "select distinct f.* from filme f join sessao s on s.id_filme = f.id where s.data_sessao BETWEEN :deData AND :ateData", nativeQuery = true)
    List<FilmeEntity> findAllFilmesComSessoesEntre(LocalDateTime deData, LocalDateTime ateData);

    FilmeEntity findByNome(String nome);
}
