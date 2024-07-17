package com.alura.forum_hub.domain.resposta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosNovaResposta(
        @NotBlank
        String mensagem,

        @NotNull
        @Valid
        Long topico


) {
}
