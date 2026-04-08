package com.example.demo.controller;

import com.example.demo.dto.recurso.RecursoRequestDto;
import com.example.demo.dto.recurso.RecursoResponseDto;
import com.example.demo.service.RecursoService;
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
@RequestMapping("/recursos")
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService recursoService;

    @GetMapping
    public ResponseEntity<List<RecursoResponseDto>> listarTodos() {
        return ResponseEntity.ok(recursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recursoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RecursoResponseDto> criar(@Valid @RequestBody RecursoRequestDto request) {
        RecursoResponseDto response = recursoService.criar(request);
        return ResponseEntity.created(URI.create("/recursos/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoResponseDto> atualizar(@PathVariable Long id, @Valid @RequestBody RecursoRequestDto request) {
        return ResponseEntity.ok(recursoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        recursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
