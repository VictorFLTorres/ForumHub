package com.alura.Forumhub.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String senha;

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public @NotEmpty String getSenha() {
        return senha;
    }

    public void setSenha(@NotEmpty String senha) {
        this.senha = senha;
    }
}
