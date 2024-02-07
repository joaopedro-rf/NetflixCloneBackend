package com.joao.netflixClone.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record NetflixUserRegisterDTO(@NotNull String username, @NotNull @Email String email, @NotNull String password) {
    public NetflixUserRegisterDTO(@NotNull String username, @NotNull @Email String email, @NotNull String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public @NotNull String username() {
        return this.username;
    }

    public @NotNull @Email String email() {
        return this.email;
    }

    public @NotNull String password() {
        return this.password;
    }
}