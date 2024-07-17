package com.alura.forum_hub.domain.usuario;

public record DadosCadastro(String email) {
    public DadosCadastro(Usuario usuario) {
        this(usuario.getEmail());
    }
}