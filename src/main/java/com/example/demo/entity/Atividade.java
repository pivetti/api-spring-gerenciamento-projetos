package com.example.demo.entity;

import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusAtividade;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "atividades")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusAtividade status;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private LocalDate dataInicio;

    private LocalDate prazo;

    private LocalDate dataConclusao;

    private Integer percentualConclusao;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Participante responsavel;

    @OneToMany(mappedBy = "atividade")
    @Builder.Default
    private List<Custo> custos = new ArrayList<>();
}
