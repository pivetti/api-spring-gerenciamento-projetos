package com.example.demo.repository;

import com.example.demo.entity.Recurso;
import com.example.demo.enums.TipoRecurso;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    List<Recurso> findByProjetoIdAndTipo(Long projetoId, TipoRecurso tipo);

    List<Recurso> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT r FROM Recurso r WHERE r.custoUnitario >= :valor")
    List<Recurso> buscarComCustoUnitarioMinimo(@Param("valor") BigDecimal valor);
}
