package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.ValidacaoException;
import com.alura.forum_hub.domain.curso.Curso;
import com.alura.forum_hub.domain.curso.CursoRepositorio;
import com.alura.forum_hub.domain.resposta.DadosNovaResposta;
import com.alura.forum_hub.domain.usuario.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private AuthenticationService authenticationService;

    public Curso getCurso(DadosNovoTopico dadosNovoTopico) {
        var curso = cursoRepositorio.getCurso(dadosNovoTopico.curso());
        if (curso == null) {
            throw new ValidacaoException("Curso não encontrado. Cadastre curso antes!");
        }
        return curso;
    }

    public DadosDetalhamentoTopico getDadosDetalhamentoTopicoAtualizado(Long id, DadosAtualizarTopico dadosAtualizarTopico, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().updateTopic(dadosAtualizarTopico);
        topicoRepository.save(topico.get());
        var dadosTopicoAtualizado = new DadosDetalhamentoTopico(topico.get());
        return dadosTopicoAtualizado;
    }

    public DadosDetalhamentoTopico getDadosDetalhamentoTopicoDeletado(Long id, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().deleteTopic();
        topicoRepository.save(topico.get());
        var dadosTopicoDeletado = new DadosDetalhamentoTopico(topico.get());
        return dadosTopicoDeletado;
    }

    public Optional<Topico> getTopico(DadosNovaResposta dadosNovaResposta) {
        var topico = topicoRepository.findById(dadosNovaResposta.topico());
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado.");
        }
        return topico;
    }
}
