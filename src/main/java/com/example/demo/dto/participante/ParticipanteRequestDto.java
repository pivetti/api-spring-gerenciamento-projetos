package com.example.demo.dto.participante;

import com.example.demo.enums.PapelAcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ParticipanteRequestDto {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long projetoId;

    @NotBlank
    @Size(max = 100)
    private String funcaoNoProjeto;

    @NotNull
    private PapelAcesso papelAcesso;

    @NotNull
    private Boolean ativo;
}
