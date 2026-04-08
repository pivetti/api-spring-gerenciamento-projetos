package com.example.demo.dto.usuario;

import com.example.demo.enums.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private PerfilUsuario perfil;
}
