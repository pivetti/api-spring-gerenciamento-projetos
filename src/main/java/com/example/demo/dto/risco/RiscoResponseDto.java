package com.example.demo.dto.risco;

import com.example.demo.enums.CategoriaRisco;
import com.example.demo.enums.StatusRisco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RiscoResponseDto {
    private Long id;
    private String titulo;
    private String descricao;
    private CategoriaRisco categoria;
    private Integer probabilidade;
    private Integer impacto;
    private Integer criticidade;
    private StatusRisco status;
    private String estrategiaResposta;
    private String planoMitigacao;
    private Long projetoId;
    private String projetoNome;
}
