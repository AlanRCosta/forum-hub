package com.alura.forum_hub.domain.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank
        @Valid
        String nome,
        @NotBlank
        @Valid
        String email,
        @NotBlank
        @Valid
        String senha

) {
}
