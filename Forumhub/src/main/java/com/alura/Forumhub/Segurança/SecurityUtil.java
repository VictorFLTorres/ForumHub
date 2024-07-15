package com.alura.Forumhub.Segurança;

import com.alura.Forumhub.Principais.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Usuario getUsuarioAutenticado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
