package com.example.demo.dto.atividade;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusAtividade;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AtividadeResponseDto {
    private Long id;
    private String titulo;
    private String descricao;
    private StatusAtividade status;
    private Prioridade prioridade;
    private LocalDate dataInicio;
    private LocalDate prazo;
    private LocalDate dataConclusao;
    private Integer percentualConclusao;
    private Long projetoId;
    private String projetoNome;
    private Long responsavelId;
    private String responsavelNome;
}
