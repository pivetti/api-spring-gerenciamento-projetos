package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import com.example.demo.enums.PerfilUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByPerfil(PerfilUsuario perfil);

    @Query(value = "SELECT * FROM usuarios WHERE nome LIKE CONCAT('%', :trecho, '%')", nativeQuery = true)
    List<Usuario> buscarPorTrechoNoNome(@Param("trecho") String trecho);
}
