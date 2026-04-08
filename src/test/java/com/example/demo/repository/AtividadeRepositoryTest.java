package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entity.Atividade;
import com.example.demo.enums.StatusAtividade;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class AtividadeRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Test
    void deveInserirAtividadeComRelacionamentos() {
        AtividadeContexto contexto = criarAtividadeContexto();

        Atividade atividade = Atividade.builder()
            .titulo("Implementar modulo")
            .descricao("Criar testes")
            .status(StatusAtividade.NAO_INICIADA)
            .projeto(contexto.projeto())
            .responsavel(contexto.participante())
            .percentualConclusao(0)
            .build();

        Atividade salva = atividadeRepository.save(atividade);

        assertThat(salva.getId()).isNotNull();
        assertThat(salva.getResponsavel().getId()).isEqualTo(contexto.participante().getId());
    }

    @Test
    void deveAtualizarAtividade() {
        AtividadeContexto contexto = criarAtividadeContexto();
        Atividade atividade = contexto.atividade();
        atividade.setStatus(StatusAtividade.CONCLUIDA);
        atividade.setPercentualConclusao(100);

        atividadeRepository.save(atividade);
        flushAndClear();

        Atividade atualizada = atividadeRepository.findById(atividade.getId()).orElseThrow();
        assertThat(atualizada.getStatus()).isEqualTo(StatusAtividade.CONCLUIDA);
        assertThat(atualizada.getPercentualConclusao()).isEqualTo(100);
    }

    @Test
    void deveRemoverAtividade() {
        Atividade atividade = criarAtividadeContexto().atividade();

        atividadeRepository.deleteById(atividade.getId());
        flushAndClear();

        assertThat(atividadeRepository.findById(atividade.getId())).isEmpty();
    }

    @Test
    void deveListarEBuscarAtividades() {
        AtividadeContexto contexto = criarAtividadeContexto();
        Atividade atividade = contexto.atividade();

        List<Atividade> porStatus = atividadeRepository.findByProjetoIdAndStatus(
            contexto.projeto().getId(),
            StatusAtividade.EM_ANDAMENTO
        );
        List<Atividade> porResponsavel = atividadeRepository.findByResponsavelId(contexto.participante().getId());
        List<Atividade> porPercentual = atividadeRepository.buscarComPercentualMinimo(30);

        assertThat(atividadeRepository.findAll()).hasSize(1);
        assertThat(atividadeRepository.findById(atividade.getId())).contains(atividade);
        assertThat(porStatus).hasSize(1);
        assertThat(porResponsavel).hasSize(1);
        assertThat(porPercentual).hasSize(1);
    }
}
