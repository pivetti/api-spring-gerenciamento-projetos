package com.example.demo.repository;

import com.example.demo.entity.Custo;
import com.example.demo.enums.TipoCusto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustoRepository extends JpaRepository<Custo, Long> {

    List<Custo> findByProjetoIdAndTipo(Long projetoId, TipoCusto tipo);

    List<Custo> findByAtividadeId(Long atividadeId);

    @Query(value = "SELECT * FROM custos WHERE valor_real > valor_previsto", nativeQuery = true)
    List<Custo> buscarCustosAcimaDoPrevisto();
}
