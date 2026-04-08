package com.example.demo.dto.recurso;

import com.example.demo.enums.TipoRecurso;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RecursoResponseDto {
    private Long id;
    private String nome;
    private TipoRecurso tipo;
    private String descricao;
    private Integer quantidade;
    private BigDecimal custoUnitario;
    private Long projetoId;
    private String projetoNome;
}
