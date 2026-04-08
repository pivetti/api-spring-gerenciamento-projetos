package com.example.demo.dto.atividade;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusAtividade;
import jakarta.validation.constraints.Max;
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
public class AtividadePatchRequestDto {

    @Size(max = 150)
    private String titulo;

    @Size(max = 1000)
    private String descricao;

    private StatusAtividade status;

    private Prioridade prioridade;

    private LocalDate dataInicio;

    private LocalDate prazo;

    private LocalDate dataConclusao;

    @Max(100)
    private Integer percentualConclusao;

    private Long projetoId;

    private Long responsavelId;
}
