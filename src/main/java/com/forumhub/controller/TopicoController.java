package com.forumhub.controller;

import com.forumhub.dto.DadosCadastroTopico;
import com.forumhub.model.Curso;
import com.forumhub.model.Topico;
import com.forumhub.model.Usuario;
import com.forumhub.repository.CursoRepository;
import com.forumhub.repository.TopicoRepository;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository,
                            CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    // Listar todos os tópicos (público)
    @GetMapping
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    // Buscar tópico por ID (público)
    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscar(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar tópico (requer autenticação)
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid DadosCadastroTopico dados,
                                   @AuthenticationPrincipal Usuario usuarioLogado) {

        // Verifica duplicidade de tópico
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Já existe um tópico com esse título e mensagem.");
        }

        Curso curso = cursoRepository.findByNome(dados.nomeCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(usuarioLogado);
        topico.setCurso(curso);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(true);

        topicoRepository.save(topico);

        return ResponseEntity.ok(topico);
    }

    // Atualizar tópico (requer autenticação - apenas autor)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody @Valid DadosCadastroTopico dados,
                                       @AuthenticationPrincipal Usuario usuarioLogado) {

        return topicoRepository.findById(id)
                .map(topico -> {
                    if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
                        return ResponseEntity.status(403).body("Você não tem permissão para alterar este tópico.");
                    }

                    topico.setTitulo(dados.titulo());
                    topico.setMensagem(dados.mensagem());

                    topicoRepository.save(topico);

                    return ResponseEntity.ok(topico);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar tópico (requer autenticação - apenas autor)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id,
                                     @AuthenticationPrincipal Usuario usuarioLogado) {

        return topicoRepository.findById(id)
                .map(topico -> {
                    if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
                        return ResponseEntity.status(403).body("Você não tem permissão para deletar este tópico.");
                    }

                    topicoRepository.delete(topico);

                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}