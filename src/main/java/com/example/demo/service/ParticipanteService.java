package com.example.demo.service;

import com.example.demo.dto.participante.ParticipanteRequestDto;
import com.example.demo.dto.participante.ParticipanteResponseDto;
import com.example.demo.entity.Participante;
import com.example.demo.entity.Projeto;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ParticipanteRepository;
import com.example.demo.repository.ProjetoRepository;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProjetoRepository projetoRepository;

    public List<ParticipanteResponseDto> listarTodos() {
        return participanteRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ParticipanteResponseDto buscarPorId(Long id) {
        return toResponse(buscarParticipante(id));
    }

    public ParticipanteResponseDto criar(ParticipanteRequestDto request) {
        Participante participante = Participante.builder().build();
        preencherCampos(participante, request);
        return toResponse(participanteRepository.save(participante));
    }

    public ParticipanteResponseDto atualizar(Long id, ParticipanteRequestDto request) {
        Participante participante = buscarParticipante(id);
        preencherCampos(participante, request);
        return toResponse(participanteRepository.save(participante));
    }

    public void deletar(Long id) {
        participanteRepository.delete(buscarParticipante(id));
    }

    private void preencherCampos(Participante participante, ParticipanteRequestDto request) {
        participante.setUsuario(buscarUsuario(request.getUsuarioId()));
        participante.setProjeto(buscarProjeto(request.getProjetoId()));
        participante.setFuncaoNoProjeto(request.getFuncaoNoProjeto());
        participante.setPapelAcesso(request.getPapelAcesso());
        participante.setAtivo(request.getAtivo());
    }

    private Participante buscarParticipante(Long id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Participante nao encontrado com id " + id));
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario nao encontrado com id " + id));
    }

    private Projeto buscarProjeto(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Projeto nao encontrado com id " + id));
    }

    private ParticipanteResponseDto toResponse(Participante participante) {
        return ParticipanteResponseDto.builder()
                .id(participante.getId())
                .usuarioId(participante.getUsuario().getId())
                .usuarioNome(participante.getUsuario().getNome())
                .projetoId(participante.getProjeto().getId())
                .projetoNome(participante.getProjeto().getNome())
                .funcaoNoProjeto(participante.getFuncaoNoProjeto())
                .papelAcesso(participante.getPapelAcesso())
                .ativo(participante.getAtivo())
                .build();
    }
}
