package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.resposta.DadosRespostaTopico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosTopico(Long id,
                          String titulo,
                          String mensagem,
                          LocalDateTime dataCriacao,
                          String nomeAutor,
                          Status status,
                          List<DadosRespostaTopico> respostas) {
    public DadosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getAutor().getNome(), topico.getStatus(), topico.getRespostas().stream().map(DadosRespostaTopico::new).collect(Collectors.toList()));
    }
}
