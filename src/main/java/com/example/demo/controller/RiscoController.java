package com.example.demo.controller;

import com.example.demo.dto.risco.RiscoRequestDto;
import com.example.demo.dto.risco.RiscoResponseDto;
import com.example.demo.service.RiscoService;
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
@RequestMapping("/riscos")
@RequiredArgsConstructor
public class RiscoController {

    private final RiscoService riscoService;

    @GetMapping
    public ResponseEntity<List<RiscoResponseDto>> listarTodos() {
        return ResponseEntity.ok(riscoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiscoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(riscoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RiscoResponseDto> criar(@Valid @RequestBody RiscoRequestDto request) {
        RiscoResponseDto response = riscoService.criar(request);
        return ResponseEntity.created(URI.create("/riscos/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiscoResponseDto> atualizar(@PathVariable Long id, @Valid @RequestBody RiscoRequestDto request) {
        return ResponseEntity.ok(riscoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        riscoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
