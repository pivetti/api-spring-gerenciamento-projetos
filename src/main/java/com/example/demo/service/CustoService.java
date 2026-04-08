package com.example.demo.service;

import com.example.demo.dto.custo.CustoRequestDto;
import com.example.demo.dto.custo.CustoResponseDto;
import com.example.demo.entity.Atividade;
import com.example.demo.entity.Custo;
import com.example.demo.entity.Projeto;
import com.example.demo.entity.Recurso;
import com.example.demo.repository.AtividadeRepository;
import com.example.demo.repository.CustoRepository;
import com.example.demo.repository.ProjetoRepository;
import com.example.demo.repository.RecursoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustoService {

    private final CustoRepository custoRepository;
    private final ProjetoRepository projetoRepository;
    private final AtividadeRepository atividadeRepository;
    private final RecursoRepository recursoRepository;

    public List<CustoResponseDto> listarTodos() {
        return custoRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CustoResponseDto buscarPorId(Long id) {
        return toResponse(buscarCusto(id));
    }

    public CustoResponseDto criar(CustoRequestDto request) {
        Custo custo = Custo.builder().build();
        preencherCampos(custo, request);
        return toResponse(custoRepository.save(custo));
    }

    public CustoResponseDto atualizar(Long id, CustoRequestDto request) {
        Custo custo = buscarCusto(id);
        preencherCampos(custo, request);
        return toResponse(custoRepository.save(custo));
    }

    public void deletar(Long id) {
        custoRepository.delete(buscarCusto(id));
    }

    private void preencherCampos(Custo custo, CustoRequestDto request) {
        custo.setDescricao(request.getDescricao());
        custo.setTipo(request.getTipo());
        custo.setValorPrevisto(request.getValorPrevisto());
        custo.setValorReal(request.getValorReal());
        custo.setDataLancamento(request.getDataLancamento());
        custo.setProjeto(buscarProjeto(request.getProjetoId()));
        custo.setAtividade(request.getAtividadeId() != null ? buscarAtividade(request.getAtividadeId()) : null);
        custo.setRecurso(request.getRecursoId() != null ? buscarRecurso(request.getRecursoId()) : null);
    }

    private Custo buscarCusto(Long id) {
        return custoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Custo nao encontrado com id " + id));
    }

    private Projeto buscarProjeto(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto nao encontrado com id " + id));
    }

    private Atividade buscarAtividade(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Atividade nao encontrada com id " + id));
    }

    private Recurso buscarRecurso(Long id) {
        return recursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Recurso nao encontrado com id " + id));
    }

    private CustoResponseDto toResponse(Custo custo) {
        return CustoResponseDto.builder()
                .id(custo.getId())
                .descricao(custo.getDescricao())
                .tipo(custo.getTipo())
                .valorPrevisto(custo.getValorPrevisto())
                .valorReal(custo.getValorReal())
                .dataLancamento(custo.getDataLancamento())
                .projetoId(custo.getProjeto().getId())
                .projetoNome(custo.getProjeto().getNome())
                .atividadeId(custo.getAtividade() != null ? custo.getAtividade().getId() : null)
                .atividadeTitulo(custo.getAtividade() != null ? custo.getAtividade().getTitulo() : null)
                .recursoId(custo.getRecurso() != null ? custo.getRecurso().getId() : null)
                .recursoNome(custo.getRecurso() != null ? custo.getRecurso().getNome() : null)
                .build();
    }
}
