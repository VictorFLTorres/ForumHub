package com.alura.Forumhub.Servicos;

import com.alura.Forumhub.Principais.Topico;
import com.alura.Forumhub.Repositorios.TopicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoServico {

    @Autowired
    private TopicoRepositorio topicoRepositorio;

    public Topico salvar(Topico topico) {
        Optional<Topico> existente = topicoRepositorio.findByTituloAndMensagem(topico.getTitulo(), topico.getMensagem());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Tópico com título e mensagem já existe");
        }
        topico.setDataCriacao(LocalDateTime.now());
        topico.setEstado("ATIVO");
        return topicoRepositorio.save(topico);
    }

    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepositorio.findById(id);
    }

    public List<Topico> buscarTodos() {
        return topicoRepositorio.findAll();
    }

    public void deletarPorId(Long id) {
        topicoRepositorio.deleteById(id);
    }
}
