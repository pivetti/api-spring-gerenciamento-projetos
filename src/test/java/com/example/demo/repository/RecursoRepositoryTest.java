package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entity.Projeto;
import com.example.demo.entity.Recurso;
import com.example.demo.enums.TipoRecurso;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class RecursoRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private RecursoRepository recursoRepository;

    @Test
    void deveInserirRecursoComRelacionamento() {
        Projeto projeto = persistProjeto();

        Recurso recurso = Recurso.builder()
            .nome("Notebook")
            .tipo(TipoRecurso.TECNOLOGICO)
            .descricao("Equipamento principal")
            .quantidade(10)
            .custoUnitario(BigDecimal.valueOf(3500))
            .projeto(projeto)
            .build();

        Recurso salvo = recursoRepository.save(recurso);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getProjeto().getId()).isEqualTo(projeto.getId());
    }

    @Test
    void deveAtualizarRecurso() {
        Recurso recurso = persistRecurso(persistProjeto());
        recurso.setQuantidade(12);
        recurso.setCustoUnitario(BigDecimal.valueOf(999.99));

        recursoRepository.save(recurso);
        flushAndClear();

        Recurso atualizado = recursoRepository.findById(recurso.getId()).orElseThrow();
        assertThat(atualizado.getQuantidade()).isEqualTo(12);
        assertThat(atualizado.getCustoUnitario()).isEqualByComparingTo("999.99");
    }

    @Test
    void deveRemoverRecurso() {
        Recurso recurso = persistRecurso(persistProjeto());

        recursoRepository.deleteById(recurso.getId());
        flushAndClear();

        assertThat(recursoRepository.findById(recurso.getId())).isEmpty();
    }

    @Test
    void deveListarEBuscarRecursos() {
        Projeto projeto = persistProjeto();
        Recurso recurso = persistRecurso(projeto);

        List<Recurso> porTipo = recursoRepository.findByProjetoIdAndTipo(projeto.getId(), TipoRecurso.TECNOLOGICO);
        List<Recurso> porNome = recursoRepository.findByNomeContainingIgnoreCase("Recurso");
        List<Recurso> porCusto = recursoRepository.buscarComCustoUnitarioMinimo(BigDecimal.valueOf(700));

        assertThat(recursoRepository.findAll()).hasSize(1);
        assertThat(recursoRepository.findById(recurso.getId())).contains(recurso);
        assertThat(porTipo).hasSize(1);
        assertThat(porNome).hasSize(1);
        assertThat(porCusto).hasSize(1);
    }
}
