package com.forumhub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao;

    private Boolean status = true;

    @ManyToOne
    @JsonIgnoreProperties({"senha", "authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "username", "password"})
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }

    public String getMensagem() { return mensagem; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }

    public Boolean getStatus() { return status; }

    public Usuario getAutor() { return autor; }

    public Curso getCurso() { return curso; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public void setStatus(Boolean status) { this.status = status; }

    public void setAutor(Usuario autor) { this.autor = autor; }

    public void setCurso(Curso curso) { this.curso = curso; }
}