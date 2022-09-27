package com.entra21.grupo1.view.repository;

import com.entra21.grupo1.model.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
    Optional<PessoaEntity> findByLogin(String login);
    Optional<PessoaEntity> findByNome(String nome);
}
