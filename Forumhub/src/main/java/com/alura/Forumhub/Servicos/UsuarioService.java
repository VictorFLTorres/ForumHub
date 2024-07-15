package com.alura.Forumhub.Servicos;

import com.alura.Forumhub.Principais.Usuario;
import com.alura.Forumhub.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario criarUsuario(String email, String senha, String role) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setRole(role);
        return usuarioRepositorio.save(usuario);
    }

    public Usuario atualizarSenha(String email, String novaSenha) {
        Usuario usuario = usuarioRepositorio.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setSenha(novaSenha);
        return usuarioRepositorio.save(usuario);
    }
}
