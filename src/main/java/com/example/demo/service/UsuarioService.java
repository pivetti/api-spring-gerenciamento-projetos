package com.example.demo.service;

import com.example.demo.dto.usuario.UsuarioRequestDto;
import com.example.demo.dto.usuario.UsuarioResponseDto;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDto> listarTodos() {
        return usuarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UsuarioResponseDto buscarPorId(Long id) {
        return toResponse(buscarUsuario(id));
    }

    public UsuarioResponseDto criar(UsuarioRequestDto request) {
        Usuario usuario = Usuario.builder().build();
        preencherCampos(usuario, request);
        return toResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDto atualizar(Long id, UsuarioRequestDto request) {
        Usuario usuario = buscarUsuario(id);
        preencherCampos(usuario, request);
        return toResponse(usuarioRepository.save(usuario));
    }

    public void deletar(Long id) {
        usuarioRepository.delete(buscarUsuario(id));
    }

    private void preencherCampos(Usuario usuario, UsuarioRequestDto request) {
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuario.setTelefone(request.getTelefone());
        usuario.setPerfil(request.getPerfil());
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario nao encontrado com id " + id));
    }

    private UsuarioResponseDto toResponse(Usuario usuario) {
        return UsuarioResponseDto.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .perfil(usuario.getPerfil())
                .build();
    }
}
