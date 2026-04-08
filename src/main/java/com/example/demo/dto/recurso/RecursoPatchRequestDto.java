package com.example.demo.dto.recurso;

import com.example.demo.enums.TipoRecurso;
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
public class RecursoPatchRequestDto {

    @Size(max = 120)
    private String nome;

    private TipoRecurso tipo;

    @Size(max = 1000)
    private String descricao;

    @Positive
    private Integer quantidade;

    @PositiveOrZero
    private BigDecimal custoUnitario;

    private Long projetoId;
}
