package com.example.demo.entity;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusProjeto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String objetivo;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private BigDecimal orcamentoPrevisto;

    private Integer percentualConcluido;

    @OneToMany(mappedBy = "projeto")
    @Builder.Default
    private List<Participante> participantes = new ArrayList<>();

    @OneToMany(mappedBy = "projeto")
    @Builder.Default
    private List<Atividade> atividades = new ArrayList<>();

    @OneToMany(mappedBy = "projeto")
    @Builder.Default
    private List<Risco> riscos = new ArrayList<>();

    @OneToMany(mappedBy = "projeto")
    @Builder.Default
    private List<Recurso> recursos = new ArrayList<>();

    @OneToMany(mappedBy = "projeto")
    @Builder.Default
    private List<Custo> custos = new ArrayList<>();
}
