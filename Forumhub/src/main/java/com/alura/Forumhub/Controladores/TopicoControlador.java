package com.alura.Forumhub.Controladores;

import com.alura.Forumhub.DTOS.TopicoListagemDTO;
import com.alura.Forumhub.Principais.Topico;
import com.alura.Forumhub.Principais.Usuario;
import com.alura.Forumhub.Repositorios.TopicoRepositorio;
import com.alura.Forumhub.Segurança.SecurityUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoControlador {

    @Autowired
    private TopicoRepositorio topicoRepositorio;

    @GetMapping
    public List<TopicoListagemDTO> listar() {
        List<Topico> topicos = topicoRepositorio.findAll();
        return topicos.stream().map(topico -> new TopicoListagemDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> detalhar(@PathVariable Long id) {
        return topicoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> cadastrar(@RequestBody Map<String, String> request) {
        String titulo = request.get("titulo");
        String mensagem = request.get("mensagem");
        String curso = request.get("curso");

        if (titulo == null || mensagem == null || curso == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario autor = SecurityUtil.getUsuarioAutenticado();

        Topico topico = new Topico();
        topico.setTitulo(titulo);
        topico.setMensagem(mensagem);
        topico.setCurso(curso);
        topico.setAutor(autor);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setEstado("ATIVO");

        Topico novoTopico = topicoRepositorio.save(topico);
        return ResponseEntity.ok(novoTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid Topico topicoAtualizado) {
        return topicoRepositorio.findById(id)
                .map(topico -> {
                    topico.setTitulo(topicoAtualizado.getTitulo());
                    topico.setMensagem(topicoAtualizado.getMensagem());
                    topico.setCurso(topicoAtualizado.getCurso());
                    topico.setEstado(topicoAtualizado.getEstado());
                    return ResponseEntity.ok(topicoRepositorio.save(topico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return topicoRepositorio.findById(id)
                .map(topico -> {
                    topicoRepositorio.delete(topico);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}