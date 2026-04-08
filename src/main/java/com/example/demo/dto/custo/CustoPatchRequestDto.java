package com.example.demo.dto.custo;

import com.example.demo.enums.TipoCusto;
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
public class CustoPatchRequestDto {

    @Size(max = 255)
    private String descricao;

    private TipoCusto tipo;

    @PositiveOrZero
    private BigDecimal valorPrevisto;

    @PositiveOrZero
    private BigDecimal valorReal;

    private LocalDate dataLancamento;

    private Long projetoId;

    private Long atividadeId;

    private Long recursoId;
}
