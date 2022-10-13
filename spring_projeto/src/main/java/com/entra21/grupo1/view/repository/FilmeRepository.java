package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.FilmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {

    public List<FilmeEntity> findAllBySessoes_Id(Long dataSessao);

    @Query( value = "select distinct f.* from filme f join sessao s on s.id_filme = f.id where s.data_sessao >= :data", nativeQuery = true)
    List<FilmeEntity> findAllFilmesComSessoesDepois(@Param("data") LocalDateTime data);

    @Query( value = "select distinct f.* from filme f join sessao s on s.id_filme = f.id where s.data_sessao BETWEEN :deData AND :ateData", nativeQuery = true)
    List<FilmeEntity> findAllFilmesComSessoesEntre(LocalDateTime deData, LocalDateTime ateData);

    Optional<FilmeEntity> findByNome(String nome);

    @Query(value = "SELECT DISTINCT f.* FROM filme f JOIN sessao s ON s.id_filme = f.id INNER JOIN filme_genero fg ON fg.id_filme = f.id INNER JOIN genero g ON g.id = fg.id_genero WHERE g.nome = :genero AND s.data_sessao >= :data", nativeQuery = true)
    List<FilmeEntity> findAllFilmesDeGeneroComSessoesDepois(@Param("genero") String genero, @Param("data") LocalDateTime data);

    @Query(value = "select * from (select distinct f.*, avg(a.rating) as avg_rat from filme f join sessao s on s.id_filme = f.id join avaliacao a on a.id_filme = f.id where s.data_sessao >= :data group by f.id, f.nome) x where x.avg_rat >= :nota", nativeQuery = true)
    List<FilmeEntity> findAllFilmesDeNotaComSessoesDepois(@Param("nota") Double nota, @Param("data") LocalDateTime data);

    @Query(value = "SELECT * FROM (SELECT DISTINCT f.*, avg(a.rating) as avg_rat FROM filme f JOIN sessao s ON s.id_filme = f.id INNER JOIN filme_genero fg ON fg.id_filme = f.id INNER JOIN genero g ON g.id = fg.id_genero JOIN avaliacao a ON a.id_filme = f.id WHERE g.nome = :genero AND s.data_sessao >= :data group by f.id, f.nome) x where x.avg_rat >= :nota", nativeQuery = true)
    List<FilmeEntity> findAllFilmesDeGeneroENotaComSessoesDepois(@Param("genero") String genero, @Param("nota") Double nota, @Param("data") LocalDateTime data);
}
