package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.entity.Participante;
import com.example.demo.enums.PapelAcesso;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryIntegrationTest
class ParticipanteRepositoryTest extends RepositoryTestSupport {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Test
    void deveInserirParticipanteComRelacionamentos() {
        ProjetoContexto contexto = criarProjetoContexto();
        Participante participante = Participante.builder()
            .usuario(contexto.usuario())
            .projeto(contexto.projeto())
            .funcaoNoProjeto("Desenvolvedor")
            .papelAcesso(PapelAcesso.COORDENADOR)
            .ativo(true)
            .build();

        Participante salvo = participanteRepository.save(participante);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getUsuario().getId()).isEqualTo(contexto.usuario().getId());
        assertThat(salvo.getProjeto().getId()).isEqualTo(contexto.projeto().getId());
    }

    @Test
    void deveAtualizarParticipante() {
        Participante participante = persistParticipante(persistUsuario(), persistProjeto());
        participante.setFuncaoNoProjeto("Tech Lead");
        participante.setAtivo(false);

        participanteRepository.save(participante);
        flushAndClear();

        Participante atualizado = participanteRepository.findById(participante.getId()).orElseThrow();
        assertThat(atualizado.getFuncaoNoProjeto()).isEqualTo("Tech Lead");
        assertThat(atualizado.getAtivo()).isFalse();
    }

    @Test
    void deveRemoverParticipante() {
        Participante participante = persistParticipante(persistUsuario(), persistProjeto());

        participanteRepository.deleteById(participante.getId());
        flushAndClear();

        assertThat(participanteRepository.findById(participante.getId())).isEmpty();
    }

    @Test
    void deveListarEBuscarParticipantes() {
        ProjetoContexto contexto = criarProjetoContexto();
        Participante participante = contexto.participante();

        List<Participante> porProjeto = participanteRepository.findByProjetoId(contexto.projeto().getId());
        List<Participante> porUsuario = participanteRepository.findByUsuarioId(contexto.usuario().getId());
        List<Participante> porProjetoEPapel = participanteRepository.buscarPorProjetoEPapel(
            contexto.projeto().getId(),
            PapelAcesso.EXECUTOR.name()
        );

        assertThat(participanteRepository.findAll()).hasSize(1);
        assertThat(participanteRepository.findById(participante.getId())).contains(participante);
        assertThat(porProjeto).hasSize(1);
        assertThat(porUsuario).hasSize(1);
        assertThat(porProjetoEPapel).hasSize(1);
    }
}
