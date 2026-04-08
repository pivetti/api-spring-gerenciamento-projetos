package com.example.demo.controller;

import com.example.demo.dto.custo.CustoRequestDto;
import com.example.demo.dto.custo.CustoResponseDto;
import com.example.demo.service.CustoService;
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
@RequestMapping("/custos")
@RequiredArgsConstructor
public class CustoController {

    private final CustoService custoService;

    @GetMapping
    public ResponseEntity<List<CustoResponseDto>> listarTodos() {
        return ResponseEntity.ok(custoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(custoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CustoResponseDto> criar(@Valid @RequestBody CustoRequestDto request) {
        CustoResponseDto response = custoService.criar(request);
        return ResponseEntity.created(URI.create("/custos/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustoResponseDto> atualizar(@PathVariable Long id, @Valid @RequestBody CustoRequestDto request) {
        return ResponseEntity.ok(custoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        custoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
