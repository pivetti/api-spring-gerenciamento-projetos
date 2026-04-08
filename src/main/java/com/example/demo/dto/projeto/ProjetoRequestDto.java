package com.example.demo.dto.projeto;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusProjeto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
public class ProjetoRequestDto {

    @NotBlank
    @Size(max = 150)
    private String nome;

    @Size(max = 1000)
    private String descricao;

    @Size(max = 1000)
    private String objetivo;

    @NotNull
    private StatusProjeto status;

    @NotNull
    private Prioridade prioridade;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @PositiveOrZero
    private BigDecimal orcamentoPrevisto;

    @NotNull
    @Max(100)
    private Integer percentualConcluido;
}
