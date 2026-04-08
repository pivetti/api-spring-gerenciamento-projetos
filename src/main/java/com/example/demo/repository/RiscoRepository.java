package com.example.demo.repository;

import com.example.demo.entity.Risco;
import com.example.demo.enums.StatusRisco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RiscoRepository extends JpaRepository<Risco, Long> {

    List<Risco> findByProjetoIdAndStatus(Long projetoId, StatusRisco status);

    List<Risco> findByCategoria(com.example.demo.enums.CategoriaRisco categoria);

    @Query(value = "SELECT * FROM riscos WHERE criticidade >= :criticidade", nativeQuery = true)
    List<Risco> buscarComCriticidadeMinima(@Param("criticidade") Integer criticidade);
}
