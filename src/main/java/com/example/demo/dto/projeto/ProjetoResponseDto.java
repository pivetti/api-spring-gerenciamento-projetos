package com.example.demo.dto.projeto;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusProjeto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProjetoResponseDto {
    private Long id;
    private String nome;
    private String descricao;
    private String objetivo;
    private StatusProjeto status;
    private Prioridade prioridade;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal orcamentoPrevisto;
    private Integer percentualConcluido;
}
