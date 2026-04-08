package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.dto.atividade.AtividadePatchRequestDto;
import com.example.demo.dto.atividade.AtividadeResponseDto;
import com.example.demo.enums.StatusAtividade;
import com.example.demo.service.AtividadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class AtividadeServicePatchTest extends RepositoryTestSupport {

    @Autowired
    private AtividadeService atividadeService;

    @Test
    void deveAtualizarSomenteCamposInformadosNoPatch() {
        AtividadeContexto contexto = criarAtividadeContexto();

        AtividadePatchRequestDto request = AtividadePatchRequestDto.builder()
            .status(StatusAtividade.CONCLUIDA)
            .percentualConclusao(100)
            .build();

        AtividadeResponseDto response = atividadeService.atualizarParcialmente(contexto.atividade().getId(), request);

        assertThat(response.getStatus()).isEqualTo(StatusAtividade.CONCLUIDA);
        assertThat(response.getPercentualConclusao()).isEqualTo(100);
        assertThat(response.getTitulo()).isEqualTo(contexto.atividade().getTitulo());
        assertThat(response.getProjetoId()).isEqualTo(contexto.projeto().getId());
        assertThat(response.getResponsavelId()).isEqualTo(contexto.participante().getId());
    }
}
