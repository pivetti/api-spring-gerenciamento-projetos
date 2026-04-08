package com.example.demo.entity;

import com.example.demo.enums.CategoriaRisco;
import com.example.demo.enums.StatusRisco;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "riscos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Risco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaRisco categoria;

    private Integer probabilidade;

    private Integer impacto;

    private Integer criticidade;

    @Enumerated(EnumType.STRING)
    private StatusRisco status;

    private String estrategiaResposta;

    private String planoMitigacao;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;
}
