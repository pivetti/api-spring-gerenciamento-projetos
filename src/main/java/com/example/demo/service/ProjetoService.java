package com.example.demo.service;

import com.example.demo.dto.projeto.ProjetoRequestDto;
import com.example.demo.dto.projeto.ProjetoResponseDto;
import com.example.demo.entity.Projeto;
import com.example.demo.repository.ProjetoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public List<ProjetoResponseDto> listarTodos() {
        return projetoRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ProjetoResponseDto buscarPorId(Long id) {
        return toResponse(buscarProjeto(id));
    }

    public ProjetoResponseDto criar(ProjetoRequestDto request) {
        Projeto projeto = Projeto.builder().build();
        preencherCampos(projeto, request);
        return toResponse(projetoRepository.save(projeto));
    }

    public ProjetoResponseDto atualizar(Long id, ProjetoRequestDto request) {
        Projeto projeto = buscarProjeto(id);
        preencherCampos(projeto, request);
        return toResponse(projetoRepository.save(projeto));
    }

    public void deletar(Long id) {
        projetoRepository.delete(buscarProjeto(id));
    }

    private void preencherCampos(Projeto projeto, ProjetoRequestDto request) {
        projeto.setNome(request.getNome());
        projeto.setDescricao(request.getDescricao());
        projeto.setObjetivo(request.getObjetivo());
        projeto.setStatus(request.getStatus());
        projeto.setPrioridade(request.getPrioridade());
        projeto.setDataInicio(request.getDataInicio());
        projeto.setDataFim(request.getDataFim());
        projeto.setOrcamentoPrevisto(request.getOrcamentoPrevisto());
        projeto.setPercentualConcluido(request.getPercentualConcluido());
    }

    private Projeto buscarProjeto(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto nao encontrado com id " + id));
    }

    private ProjetoResponseDto toResponse(Projeto projeto) {
        return ProjetoResponseDto.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .descricao(projeto.getDescricao())
                .objetivo(projeto.getObjetivo())
                .status(projeto.getStatus())
                .prioridade(projeto.getPrioridade())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .orcamentoPrevisto(projeto.getOrcamentoPrevisto())
                .percentualConcluido(projeto.getPercentualConcluido())
                .build();
    }
}
