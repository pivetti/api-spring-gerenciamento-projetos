package com.example.demo.service;

import com.example.demo.dto.atividade.AtividadeRequestDto;
import com.example.demo.dto.atividade.AtividadeResponseDto;
import com.example.demo.entity.Atividade;
import com.example.demo.entity.Participante;
import com.example.demo.entity.Projeto;
import com.example.demo.repository.AtividadeRepository;
import com.example.demo.repository.ParticipanteRepository;
import com.example.demo.repository.ProjetoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final ProjetoRepository projetoRepository;
    private final ParticipanteRepository participanteRepository;

    public List<AtividadeResponseDto> listarTodos() {
        return atividadeRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AtividadeResponseDto buscarPorId(Long id) {
        return toResponse(buscarAtividade(id));
    }

    public AtividadeResponseDto criar(AtividadeRequestDto request) {
        Atividade atividade = Atividade.builder().build();
        preencherCampos(atividade, request);
        return toResponse(atividadeRepository.save(atividade));
    }

    public AtividadeResponseDto atualizar(Long id, AtividadeRequestDto request) {
        Atividade atividade = buscarAtividade(id);
        preencherCampos(atividade, request);
        return toResponse(atividadeRepository.save(atividade));
    }

    public void deletar(Long id) {
        atividadeRepository.delete(buscarAtividade(id));
    }

    private void preencherCampos(Atividade atividade, AtividadeRequestDto request) {
        atividade.setTitulo(request.getTitulo());
        atividade.setDescricao(request.getDescricao());
        atividade.setStatus(request.getStatus());
        atividade.setPrioridade(request.getPrioridade());
        atividade.setDataInicio(request.getDataInicio());
        atividade.setPrazo(request.getPrazo());
        atividade.setDataConclusao(request.getDataConclusao());
        atividade.setPercentualConclusao(request.getPercentualConclusao());
        atividade.setProjeto(buscarProjeto(request.getProjetoId()));
        atividade.setResponsavel(
                request.getResponsavelId() != null ? buscarParticipante(request.getResponsavelId()) : null);
    }

    private Atividade buscarAtividade(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Atividade nao encontrada com id " + id));
    }

    private Projeto buscarProjeto(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto nao encontrado com id " + id));
    }

    private Participante buscarParticipante(Long id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Participante nao encontrado com id " + id));
    }

    private AtividadeResponseDto toResponse(Atividade atividade) {
        return AtividadeResponseDto.builder()
                .id(atividade.getId())
                .titulo(atividade.getTitulo())
                .descricao(atividade.getDescricao())
                .status(atividade.getStatus())
                .prioridade(atividade.getPrioridade())
                .dataInicio(atividade.getDataInicio())
                .prazo(atividade.getPrazo())
                .dataConclusao(atividade.getDataConclusao())
                .percentualConclusao(atividade.getPercentualConclusao())
                .projetoId(atividade.getProjeto().getId())
                .projetoNome(atividade.getProjeto().getNome())
                .responsavelId(atividade.getResponsavel() != null ? atividade.getResponsavel().getId() : null)
                .responsavelNome(atividade.getResponsavel() != null ? atividade.getResponsavel().getUsuario().getNome() : null)
                .build();
    }
}
