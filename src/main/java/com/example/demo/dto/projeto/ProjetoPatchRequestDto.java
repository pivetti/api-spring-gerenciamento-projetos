package com.example.demo.dto.projeto;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusProjeto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class ProjetoPatchRequestDto {

    private String nome;

    private String descricao;

    private String objetivo;

    private StatusProjeto status;

    private Prioridade prioridade;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @PositiveOrZero
    private BigDecimal orcamentoPrevisto;

    @Max(100)
    private Integer percentualConcluido;
}
