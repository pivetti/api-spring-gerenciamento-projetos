package com.example.demo.controller;

import com.example.demo.dto.participante.ParticipanteRequestDto;
import com.example.demo.dto.participante.ParticipanteResponseDto;
import com.example.demo.service.ParticipanteService;
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
@RequestMapping("/participantes")
@RequiredArgsConstructor
public class ParticipanteController {

    private final ParticipanteService participanteService;

    @GetMapping
    public ResponseEntity<List<ParticipanteResponseDto>> listarTodos() {
        return ResponseEntity.ok(participanteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(participanteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ParticipanteResponseDto> criar(@Valid @RequestBody ParticipanteRequestDto request) {
        ParticipanteResponseDto response = participanteService.criar(request);
        return ResponseEntity.created(URI.create("/participantes/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteResponseDto> atualizar(@PathVariable Long id,
            @Valid @RequestBody ParticipanteRequestDto request) {
        return ResponseEntity.ok(participanteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        participanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
