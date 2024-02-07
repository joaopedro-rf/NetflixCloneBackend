package com.joao.netflixClone.DTO;

public record LoginResponseDTO(String token) {
    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String token() {
        return this.token;
    }
}
