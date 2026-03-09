package com.forumhub.controller;

import com.forumhub.dto.DadosLogin;
import com.forumhub.model.Usuario;
import com.forumhub.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody DadosLogin dados) {
        try {
            var authenticationToken =
                    new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

            var authentication = manager.authenticate(authenticationToken);

            var usuario = (Usuario) authentication.getPrincipal();

            var token = tokenService.gerarToken(usuario);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Credenciais inválidas"));
        }
    }
}