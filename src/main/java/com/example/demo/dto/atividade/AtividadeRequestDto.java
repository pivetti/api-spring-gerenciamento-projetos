package com.example.demo.dto.atividade;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusAtividade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AtividadeRequestDto {

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @Size(max = 1000)
    private String descricao;

    @NotNull
    private StatusAtividade status;

    @NotNull
    private Prioridade prioridade;

    private LocalDate dataInicio;

    private LocalDate prazo;

    private LocalDate dataConclusao;

    @NotNull
    @Max(100)
    private Integer percentualConclusao;

    @NotNull
    private Long projetoId;

    private Long responsavelId;
}
