package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entity.Usuario;
import com.example.demo.enums.PerfilUsuario;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class UsuarioRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveInserirUsuario() {
        Usuario usuario = Usuario.builder()
            .nome("Maria Silva")
            .email("maria@teste.com")
            .senha("segredo")
            .telefone("11999998888")
            .perfil(PerfilUsuario.GERENTE_PROJETO)
            .build();

        Usuario salvo = usuarioRepository.save(usuario);

        assertThat(salvo.getId()).isNotNull();
    }

    @Test
    void deveAtualizarUsuario() {
        Usuario usuario = persistUsuario();
        usuario.setNome("Usuario Atualizado");
        usuario.setPerfil(PerfilUsuario.ADMINISTRADOR);

        usuarioRepository.save(usuario);
        flushAndClear();

        Usuario encontrado = usuarioRepository.findById(usuario.getId()).orElseThrow();
        assertThat(encontrado.getNome()).isEqualTo("Usuario Atualizado");
        assertThat(encontrado.getPerfil()).isEqualTo(PerfilUsuario.ADMINISTRADOR);
    }

    @Test
    void deveRemoverUsuario() {
        Usuario usuario = persistUsuario();

        usuarioRepository.deleteById(usuario.getId());
        flushAndClear();

        assertThat(usuarioRepository.findById(usuario.getId())).isEmpty();
    }

    @Test
    void deveListarEBuscarUsuarios() {
        Usuario usuario = persistUsuario();
        persistUsuario();

        List<Usuario> todos = usuarioRepository.findAll();
        List<Usuario> porPerfil = usuarioRepository.findByPerfil(PerfilUsuario.MEMBRO_EQUIPE);
        List<Usuario> porNome = usuarioRepository.buscarPorTrechoNoNome("Usuario");

        assertThat(todos).hasSize(2);
        assertThat(usuarioRepository.findByEmail(usuario.getEmail())).contains(usuario);
        assertThat(porPerfil).hasSize(2);
        assertThat(porNome).hasSize(2);
    }
}
