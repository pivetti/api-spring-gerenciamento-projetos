package com.example.demo.dto.risco;

import com.example.demo.enums.CategoriaRisco;
import com.example.demo.enums.StatusRisco;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
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
public class RiscoPatchRequestDto {

    @Size(max = 150)
    private String titulo;

    @Size(max = 1000)
    private String descricao;

    private CategoriaRisco categoria;

    @Positive
    @Max(5)
    private Integer probabilidade;

    @Positive
    @Max(5)
    private Integer impacto;

    @Positive
    @Max(25)
    private Integer criticidade;

    private StatusRisco status;

    @Size(max = 500)
    private String estrategiaResposta;

    @Size(max = 1000)
    private String planoMitigacao;

    private Long projetoId;
}
