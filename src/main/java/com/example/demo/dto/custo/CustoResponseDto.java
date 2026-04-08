package com.example.demo.dto.custo;

import com.example.demo.enums.TipoCusto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustoResponseDto {
    private Long id;
    private String descricao;
    private TipoCusto tipo;
    private BigDecimal valorPrevisto;
    private BigDecimal valorReal;
    private LocalDate dataLancamento;
    private Long projetoId;
    private String projetoNome;
    private Long atividadeId;
    private String atividadeTitulo;
    private Long recursoId;
    private String recursoNome;
}
