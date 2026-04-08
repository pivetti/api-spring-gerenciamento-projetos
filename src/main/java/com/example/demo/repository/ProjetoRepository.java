package com.example.demo.repository;

import com.example.demo.entity.Projeto;
import com.example.demo.enums.StatusProjeto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findByStatus(StatusProjeto status);

    List<Projeto> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT p FROM Projeto p WHERE p.orcamentoPrevisto >= :valor")
    List<Projeto> buscarComOrcamentoMinimo(@Param("valor") BigDecimal valor);
}
