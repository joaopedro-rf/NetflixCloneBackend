package com.joao.netflixClone.DTO;

public record NetflixUserDTO(String email, String password) {
    public NetflixUserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String email() {
        return this.email;
    }

    public String password() {
        return this.password;
    }
}