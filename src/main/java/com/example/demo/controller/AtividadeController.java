package com.example.demo.controller;

import com.example.demo.dto.atividade.AtividadeRequestDto;
import com.example.demo.dto.atividade.AtividadeResponseDto;
import com.example.demo.service.AtividadeService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividades")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeResponseDto>> listarTodos() {
        return ResponseEntity.ok(atividadeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atividadeService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AtividadeResponseDto> criar(@Valid @RequestBody AtividadeRequestDto request) {
        AtividadeResponseDto response = atividadeService.criar(request);
        return ResponseEntity.created(URI.create("/atividades/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeResponseDto> atualizar(@PathVariable Long id,
            @Valid @RequestBody AtividadeRequestDto request) {
        return ResponseEntity.ok(atividadeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
