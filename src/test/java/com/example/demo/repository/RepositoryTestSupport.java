package com.example.demo.repository;

import com.example.demo.entity.Atividade;
import com.example.demo.entity.Custo;
import com.example.demo.entity.Participante;
import com.example.demo.entity.Projeto;
import com.example.demo.entity.Recurso;
import com.example.demo.entity.Risco;
import com.example.demo.entity.Usuario;
import com.example.demo.enums.CategoriaRisco;
import com.example.demo.enums.PapelAcesso;
import com.example.demo.enums.PerfilUsuario;
import com.example.demo.enums.Prioridade;
import com.example.demo.enums.StatusAtividade;
import com.example.demo.enums.StatusProjeto;
import com.example.demo.enums.StatusRisco;
import com.example.demo.enums.TipoCusto;
import com.example.demo.enums.TipoRecurso;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;

abstract class RepositoryTestSupport {

    protected record ProjetoContexto(Usuario usuario, Projeto projeto, Participante participante) {
    }

    protected record AtividadeContexto(
        Usuario usuario,
        Projeto projeto,
        Participante participante,
        Atividade atividade
    ) {
    }

    protected record CustoContexto(
        Usuario usuario,
        Projeto projeto,
        Participante participante,
        Atividade atividade,
        Recurso recurso,
        Custo custo
    ) {
    }

    private final AtomicInteger sequence = new AtomicInteger(1);

    @Autowired
    protected EntityManager entityManager;

    protected Usuario persistUsuario() {
        int index = sequence.getAndIncrement();
        Usuario usuario = Usuario.builder()
            .nome("Usuario " + index)
            .email("usuario" + index + "@teste.com")
            .senha("senha123")
            .telefone("1199999000" + index)
            .perfil(PerfilUsuario.MEMBRO_EQUIPE)
            .build();
        entityManager.persist(usuario);
        entityManager.flush();
        return usuario;
    }

    protected Projeto persistProjeto() {
        int index = sequence.getAndIncrement();
        Projeto projeto = Projeto.builder()
            .nome("Projeto " + index)
            .descricao("Descricao " + index)
            .objetivo("Objetivo " + index)
            .status(StatusProjeto.EM_ANDAMENTO)
            .prioridade(Prioridade.ALTA)
            .dataInicio(LocalDate.of(2026, 1, 10))
            .dataFim(LocalDate.of(2026, 12, 20))
            .orcamentoPrevisto(BigDecimal.valueOf(10_000 + index))
            .percentualConcluido(25)
            .build();
        entityManager.persist(projeto);
        entityManager.flush();
        return projeto;
    }

    protected Participante persistParticipante(Usuario usuario, Projeto projeto) {
        return persistParticipante(usuario, projeto, PapelAcesso.EXECUTOR);
    }

    protected Participante persistParticipante(Usuario usuario, Projeto projeto, PapelAcesso papelAcesso) {
        Participante participante = Participante.builder()
            .usuario(usuario)
            .projeto(projeto)
            .funcaoNoProjeto("Analista")
            .papelAcesso(papelAcesso)
            .ativo(true)
            .build();
        entityManager.persist(participante);
        entityManager.flush();
        return participante;
    }

    protected Atividade persistAtividade(Projeto projeto, Participante responsavel) {
        int index = sequence.getAndIncrement();
        Atividade atividade = Atividade.builder()
            .titulo("Atividade " + index)
            .descricao("Descricao atividade " + index)
            .status(StatusAtividade.EM_ANDAMENTO)
            .prioridade(Prioridade.MEDIA)
            .dataInicio(LocalDate.of(2026, 2, 1))
            .prazo(LocalDate.of(2026, 3, 1))
            .percentualConclusao(40)
            .projeto(projeto)
            .responsavel(responsavel)
            .build();
        entityManager.persist(atividade);
        entityManager.flush();
        return atividade;
    }

    protected Recurso persistRecurso(Projeto projeto) {
        int index = sequence.getAndIncrement();
        Recurso recurso = Recurso.builder()
            .nome("Recurso " + index)
            .tipo(TipoRecurso.TECNOLOGICO)
            .descricao("Descricao recurso " + index)
            .quantidade(5)
            .custoUnitario(BigDecimal.valueOf(800 + index))
            .projeto(projeto)
            .build();
        entityManager.persist(recurso);
        entityManager.flush();
        return recurso;
    }

    protected Custo persistCusto(Projeto projeto, Atividade atividade, Recurso recurso) {
        int index = sequence.getAndIncrement();
        Custo custo = Custo.builder()
            .descricao("Custo " + index)
            .tipo(TipoCusto.OPERACIONAL)
            .valorPrevisto(BigDecimal.valueOf(500))
            .valorReal(BigDecimal.valueOf(650))
            .dataLancamento(LocalDate.of(2026, 2, 15))
            .projeto(projeto)
            .atividade(atividade)
            .recurso(recurso)
            .build();
        entityManager.persist(custo);
        entityManager.flush();
        return custo;
    }

    protected Risco persistRisco(Projeto projeto) {
        int index = sequence.getAndIncrement();
        Risco risco = Risco.builder()
            .titulo("Risco " + index)
            .descricao("Descricao risco " + index)
            .categoria(CategoriaRisco.PRAZO)
            .probabilidade(4)
            .impacto(5)
            .criticidade(20)
            .status(StatusRisco.EM_ANALISE)
            .estrategiaResposta("Mitigar")
            .planoMitigacao("Plano " + index)
            .projeto(projeto)
            .build();
        entityManager.persist(risco);
        entityManager.flush();
        return risco;
    }

    protected ProjetoContexto criarProjetoContexto() {
        Usuario usuario = persistUsuario();
        Projeto projeto = persistProjeto();
        Participante participante = persistParticipante(usuario, projeto);
        return new ProjetoContexto(usuario, projeto, participante);
    }

    protected AtividadeContexto criarAtividadeContexto() {
        ProjetoContexto contexto = criarProjetoContexto();
        Atividade atividade = persistAtividade(contexto.projeto(), contexto.participante());
        return new AtividadeContexto(contexto.usuario(), contexto.projeto(), contexto.participante(), atividade);
    }

    protected CustoContexto criarCustoContexto() {
        AtividadeContexto contexto = criarAtividadeContexto();
        Recurso recurso = persistRecurso(contexto.projeto());
        Custo custo = persistCusto(contexto.projeto(), contexto.atividade(), recurso);
        return new CustoContexto(
            contexto.usuario(),
            contexto.projeto(),
            contexto.participante(),
            contexto.atividade(),
            recurso,
            custo
        );
    }

    protected void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
