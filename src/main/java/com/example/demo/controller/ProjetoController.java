package com.example.demo.controller;

import com.example.demo.dto.projeto.ProjetoRequestDto;
import com.example.demo.dto.projeto.ProjetoResponseDto;
import com.example.demo.service.ProjetoService;
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
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<ProjetoResponseDto>> listarTodos() {
        return ResponseEntity.ok(projetoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(projetoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDto> criar(@Valid @RequestBody ProjetoRequestDto request) {
        ProjetoResponseDto response = projetoService.criar(request);
        return ResponseEntity.created(URI.create("/projetos/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponseDto> atualizar(@PathVariable Long id, @Valid @RequestBody ProjetoRequestDto request) {
        return ResponseEntity.ok(projetoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
