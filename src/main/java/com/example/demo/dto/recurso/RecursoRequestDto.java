package com.example.demo.dto.recurso;

import com.example.demo.enums.TipoRecurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
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
public class RecursoRequestDto {

    @NotBlank
    @Size(max = 120)
    private String nome;

    @NotNull
    private TipoRecurso tipo;

    @Size(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @PositiveOrZero
    private BigDecimal custoUnitario;

    @NotNull
    private Long projetoId;
}
