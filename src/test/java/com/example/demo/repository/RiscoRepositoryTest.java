package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entity.Projeto;
import com.example.demo.entity.Risco;
import com.example.demo.enums.CategoriaRisco;
import com.example.demo.enums.StatusRisco;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class RiscoRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private RiscoRepository riscoRepository;

    @Test
    void deveInserirRiscoComRelacionamento() {
        Projeto projeto = persistProjeto();

        Risco risco = Risco.builder()
            .titulo("Atraso no fornecedor")
            .descricao("Fornecedor pode atrasar")
            .categoria(CategoriaRisco.PRAZO)
            .probabilidade(5)
            .impacto(4)
            .criticidade(20)
            .status(StatusRisco.IDENTIFICADO)
            .projeto(projeto)
            .build();

        Risco salvo = riscoRepository.save(risco);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getProjeto().getId()).isEqualTo(projeto.getId());
    }

    @Test
    void deveAtualizarRisco() {
        Risco risco = persistRisco(persistProjeto());
        risco.setStatus(StatusRisco.MITIGADO);
        risco.setCriticidade(10);

        riscoRepository.save(risco);
        flushAndClear();

        Risco atualizado = riscoRepository.findById(risco.getId()).orElseThrow();
        assertThat(atualizado.getStatus()).isEqualTo(StatusRisco.MITIGADO);
        assertThat(atualizado.getCriticidade()).isEqualTo(10);
    }

    @Test
    void deveRemoverRisco() {
        Risco risco = persistRisco(persistProjeto());

        riscoRepository.deleteById(risco.getId());
        flushAndClear();

        assertThat(riscoRepository.findById(risco.getId())).isEmpty();
    }

    @Test
    void deveListarEBuscarRiscos() {
        Projeto projeto = persistProjeto();
        Risco risco = persistRisco(projeto);

        List<Risco> porStatus = riscoRepository.findByProjetoIdAndStatus(projeto.getId(), StatusRisco.EM_ANALISE);
        List<Risco> porCategoria = riscoRepository.findByCategoria(CategoriaRisco.PRAZO);
        List<Risco> porCriticidade = riscoRepository.buscarComCriticidadeMinima(15);

        assertThat(riscoRepository.findAll()).hasSize(1);
        assertThat(riscoRepository.findById(risco.getId())).contains(risco);
        assertThat(porStatus).hasSize(1);
        assertThat(porCategoria).hasSize(1);
        assertThat(porCriticidade).hasSize(1);
    }
}
