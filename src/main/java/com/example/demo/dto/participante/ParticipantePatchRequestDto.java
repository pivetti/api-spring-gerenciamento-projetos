package com.example.demo.dto.participante;

import com.example.demo.enums.PapelAcesso;
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
public class ParticipantePatchRequestDto {

    private Long usuarioId;

    private Long projetoId;

    @Size(max = 100)
    private String funcaoNoProjeto;

    private PapelAcesso papelAcesso;

    private Boolean ativo;
}
