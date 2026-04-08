package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entity.Atividade;
import com.example.demo.entity.Custo;
import com.example.demo.entity.Recurso;
import com.example.demo.enums.TipoCusto;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class CustoRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private CustoRepository custoRepository;

    @Test
    void deveInserirCustoComRelacionamentos() {
        AtividadeContexto contexto = criarAtividadeContexto();
        Recurso recurso = persistRecurso(contexto.projeto());

        Custo custo = Custo.builder()
            .descricao("Horas extras")
            .tipo(TipoCusto.RH)
            .valorPrevisto(BigDecimal.valueOf(1000))
            .valorReal(BigDecimal.valueOf(1200))
            .projeto(contexto.projeto())
            .atividade(contexto.atividade())
            .recurso(recurso)
            .build();

        Custo salvo = custoRepository.save(custo);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getAtividade().getId()).isEqualTo(contexto.atividade().getId());
        assertThat(salvo.getRecurso().getId()).isEqualTo(recurso.getId());
    }

    @Test
    void deveAtualizarCusto() {
        CustoContexto contexto = criarCustoContexto();
        Custo custo = contexto.custo();
        custo.setValorReal(BigDecimal.valueOf(700));
        custo.setTipo(TipoCusto.IMPREVISTO);

        custoRepository.save(custo);
        flushAndClear();

        Custo atualizado = custoRepository.findById(custo.getId()).orElseThrow();
        assertThat(atualizado.getValorReal()).isEqualByComparingTo("700");
        assertThat(atualizado.getTipo()).isEqualTo(TipoCusto.IMPREVISTO);
    }

    @Test
    void deveRemoverCusto() {
        Custo custo = criarCustoContexto().custo();

        custoRepository.deleteById(custo.getId());
        flushAndClear();

        assertThat(custoRepository.findById(custo.getId())).isEmpty();
    }

    @Test
    void deveListarEBuscarCustos() {
        CustoContexto contexto = criarCustoContexto();
        Custo custo = contexto.custo();

        List<Custo> porTipo = custoRepository.findByProjetoIdAndTipo(contexto.projeto().getId(), TipoCusto.OPERACIONAL);
        List<Custo> porAtividade = custoRepository.findByAtividadeId(contexto.atividade().getId());
        List<Custo> acimaPrevisto = custoRepository.buscarCustosAcimaDoPrevisto();

        assertThat(custoRepository.findAll()).hasSize(1);
        assertThat(custoRepository.findById(custo.getId())).contains(custo);
        assertThat(porTipo).hasSize(1);
        assertThat(porAtividade).hasSize(1);
        assertThat(acimaPrevisto).hasSize(1);
    }
}
