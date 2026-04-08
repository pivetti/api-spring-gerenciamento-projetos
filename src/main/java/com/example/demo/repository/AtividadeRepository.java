package com.example.demo.repository;

import com.example.demo.entity.Atividade;
import com.example.demo.enums.StatusAtividade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findByProjetoIdAndStatus(Long projetoId, StatusAtividade status);

    List<Atividade> findByResponsavelId(Long responsavelId);

    @Query(value = "SELECT * FROM atividades WHERE percentual_conclusao >= :percentual", nativeQuery = true)
    List<Atividade> buscarComPercentualMinimo(@Param("percentual") Integer percentual);
}
