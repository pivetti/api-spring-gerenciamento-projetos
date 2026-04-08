package com.example.demo.dto.usuario;

import com.example.demo.enums.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPatchRequestDto {

    @Size(max = 120)
    private String nome;

    @Email
    @Size(max = 150)
    private String email;

    @Size(min = 6, max = 120)
    private String senha;

    @Size(max = 20)
    private String telefone;

    private PerfilUsuario perfil;
}
