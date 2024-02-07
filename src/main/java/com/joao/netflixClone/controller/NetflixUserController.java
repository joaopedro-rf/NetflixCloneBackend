package com.joao.netflixClone.controller;


import com.joao.netflixClone.DTO.NetflixUserDTO;
import com.joao.netflixClone.DTO.NetflixUserRegisterDTO;
import com.joao.netflixClone.service.NetflixUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/users"})
public class NetflixUserController {
    @Autowired
    private NetflixUserService netflixUserService;

    public NetflixUserController() {
    }

    @PostMapping({"/login"})
    public ResponseEntity<Object> login(@RequestBody @Valid NetflixUserDTO netflixUserDTO) {
        return this.netflixUserService.login(netflixUserDTO);
    }

    @PostMapping({"/register"})
    public ResponseEntity<Object> register(@RequestBody @Valid NetflixUserRegisterDTO netflixUserRegisterDTO) {
        return this.netflixUserService.register(netflixUserRegisterDTO);
    }

    @GetMapping({"/username"})
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        return this.netflixUserService.findByUsername(username);
    }

    @DeleteMapping({"/delete"})
    public void delete(@PathVariable Long id) {
        this.netflixUserService.deleteUser(id);
    }
}
