package com.forumhub.controller;

import com.forumhub.dto.DadosCadastroCurso;
import com.forumhub.model.Curso;
import com.forumhub.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Curso> cadastrar(@RequestBody DadosCadastroCurso dados) {
        Curso curso = new Curso();
        curso.setNome(dados.nome());

        cursoRepository.save(curso);

        return ResponseEntity.ok(curso);
    }
}

