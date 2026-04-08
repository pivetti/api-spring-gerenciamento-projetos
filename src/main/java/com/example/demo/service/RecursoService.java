package com.example.demo.service;

import com.example.demo.dto.recurso.RecursoRequestDto;
import com.example.demo.dto.recurso.RecursoResponseDto;
import com.example.demo.entity.Projeto;
import com.example.demo.entity.Recurso;
import com.example.demo.repository.ProjetoRepository;
import com.example.demo.repository.RecursoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RecursoService {

    private final RecursoRepository recursoRepository;
    private final ProjetoRepository projetoRepository;

    public List<RecursoResponseDto> listarTodos() {
        return recursoRepository.findAll().stream().map(this::toResponse).toList();
    }

    public RecursoResponseDto buscarPorId(Long id) {
        return toResponse(buscarRecurso(id));
    }

    public RecursoResponseDto criar(RecursoRequestDto request) {
        Recurso recurso = Recurso.builder().build();
        preencherCampos(recurso, request);
        return toResponse(recursoRepository.save(recurso));
    }

    public RecursoResponseDto atualizar(Long id, RecursoRequestDto request) {
        Recurso recurso = buscarRecurso(id);
        preencherCampos(recurso, request);
        return toResponse(recursoRepository.save(recurso));
    }

    public void deletar(Long id) {
        recursoRepository.delete(buscarRecurso(id));
    }

    private void preencherCampos(Recurso recurso, RecursoRequestDto request) {
        recurso.setNome(request.getNome());
        recurso.setTipo(request.getTipo());
        recurso.setDescricao(request.getDescricao());
        recurso.setQuantidade(request.getQuantidade());
        recurso.setCustoUnitario(request.getCustoUnitario());
        recurso.setProjeto(buscarProjeto(request.getProjetoId()));
    }

    private Recurso buscarRecurso(Long id) {
        return recursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Recurso nao encontrado com id " + id));
    }

    private Projeto buscarProjeto(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto nao encontrado com id " + id));
    }

    private RecursoResponseDto toResponse(Recurso recurso) {
        return RecursoResponseDto.builder()
                .id(recurso.getId())
                .nome(recurso.getNome())
                .tipo(recurso.getTipo())
                .descricao(recurso.getDescricao())
                .quantidade(recurso.getQuantidade())
                .custoUnitario(recurso.getCustoUnitario())
                .projetoId(recurso.getProjeto().getId())
                .projetoNome(recurso.getProjeto().getNome())
                .build();
    }
}
