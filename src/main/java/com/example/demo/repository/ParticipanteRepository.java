package com.example.demo.repository;

import com.example.demo.entity.Participante;
import com.example.demo.enums.PapelAcesso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    List<Participante> findByProjetoId(Long projetoId);

    List<Participante> findByUsuarioId(Long usuarioId);

    @Query(value = "SELECT * FROM participantes WHERE projeto_id = :projetoId AND papel_acesso = :papel", nativeQuery = true)
    List<Participante> buscarPorProjetoEPapel(@Param("projetoId") Long projetoId, @Param("papel") String papel);
}
