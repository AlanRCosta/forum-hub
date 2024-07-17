package com.alura.forum_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

public record CursoDTO(
        @NotBlank
        String nome,

        @NotNull
        @Valid
        Categoria categoria
) {
}