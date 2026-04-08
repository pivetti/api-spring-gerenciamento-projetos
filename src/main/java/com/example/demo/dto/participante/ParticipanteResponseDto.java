package com.example.demo.dto.participante;

import com.example.demo.enums.PapelAcesso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ParticipanteResponseDto {
    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private Long projetoId;
    private String projetoNome;
    private String funcaoNoProjeto;
    private PapelAcesso papelAcesso;
    private Boolean ativo;
}
