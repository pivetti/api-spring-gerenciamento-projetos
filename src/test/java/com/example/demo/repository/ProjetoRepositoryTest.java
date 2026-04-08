package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entity.Projeto;
import com.example.demo.enums.StatusProjeto;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class ProjetoRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Test
    void deveInserirProjeto() {
        Projeto projeto = Projeto.builder()
            .nome("Portal Integrador")
            .descricao("Projeto piloto")
            .objetivo("Organizar entregas")
            .status(StatusProjeto.PLANEJADO)
            .orcamentoPrevisto(BigDecimal.valueOf(25_000))
            .build();

        Projeto salvo = projetoRepository.save(projeto);

        assertThat(salvo.getId()).isNotNull();
    }

    @Test
    void deveAtualizarProjeto() {
        Projeto projeto = persistProjeto();
        projeto.setStatus(StatusProjeto.CONCLUIDO);
        projeto.setPercentualConcluido(100);

        projetoRepository.save(projeto);
        flushAndClear();

        Projeto atualizado = projetoRepository.findById(projeto.getId()).orElseThrow();
        assertThat(atualizado.getStatus()).isEqualTo(StatusProjeto.CONCLUIDO);
        assertThat(atualizado.getPercentualConcluido()).isEqualTo(100);
    }

    @Test
    void deveRemoverProjeto() {
        Projeto projeto = persistProjeto();

        projetoRepository.deleteById(projeto.getId());
        flushAndClear();

        assertThat(projetoRepository.findById(projeto.getId())).isEmpty();
    }

    @Test
    void deveListarEBuscarProjetos() {
        Projeto projeto = persistProjeto();
        persistProjeto();

        List<Projeto> porStatus = projetoRepository.findByStatus(StatusProjeto.EM_ANDAMENTO);
        List<Projeto> porNome = projetoRepository.findByNomeContainingIgnoreCase("Projeto");
        List<Projeto> porOrcamento = projetoRepository.buscarComOrcamentoMinimo(BigDecimal.valueOf(10_000));

        assertThat(projetoRepository.findAll()).hasSize(2);
        assertThat(projetoRepository.findById(projeto.getId())).contains(projeto);
        assertThat(porStatus).hasSize(2);
        assertThat(porNome).hasSize(2);
        assertThat(porOrcamento).hasSize(2);
    }
}
