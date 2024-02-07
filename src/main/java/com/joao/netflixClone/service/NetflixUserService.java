package com.joao.netflixClone.service;



import com.joao.netflixClone.DTO.LoginResponseDTO;
import com.joao.netflixClone.DTO.NetflixUserDTO;
import com.joao.netflixClone.DTO.NetflixUserRegisterDTO;
import com.joao.netflixClone.model.NetflixUser;
import com.joao.netflixClone.repository.NetflixUserRepository;
import com.joao.netflixClone.security.TokenService;
import jakarta.validation.Valid;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class NetflixUserService {
    @Autowired
    private final NetflixUserRepository netflixUserRepository;
    private final TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public NetflixUserService(NetflixUserRepository netflixUserRepository, TokenService tokenService) {
        this.netflixUserRepository = netflixUserRepository;
        this.tokenService = tokenService;
    }

    public ResponseEntity<Object> login(@RequestBody @Valid NetflixUserDTO netflixUserDTO) {
        NetflixUser netflixUser = this.netflixUserRepository.findByEmail(netflixUserDTO.email());
        if (netflixUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else if ((new BCryptPasswordEncoder()).matches(netflixUserDTO.password(), netflixUser.getPassword())) {
            String token = this.tokenService.generateToken(netflixUser);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<Object> register(@RequestBody NetflixUserRegisterDTO netflixUserRegisterDTO) {
        if (this.netflixUserRepository.findByEmail(netflixUserRegisterDTO.email()) != null) {
            return ResponseEntity.badRequest().build();
        } else {
            String encryptPassword = (new BCryptPasswordEncoder()).encode(netflixUserRegisterDTO.password());
            NetflixUser newUser = new NetflixUser(netflixUserRegisterDTO.username(), netflixUserRegisterDTO.email(), encryptPassword);
            newUser.setCreatedAt(new Date(System.currentTimeMillis()));
            this.netflixUserRepository.save(newUser);
            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<?> findByUsername(String username) {
        NetflixUser netflixUser = this.netflixUserRepository.findByUsername(username);
        return netflixUser != null ? ResponseEntity.ok(netflixUser) : ResponseEntity.notFound().build();
    }

    public Page<NetflixUser> findAllUsers(Pageable page) {
        return this.netflixUserRepository.findAll(page);
    }

    public void deleteUser(Long id) {
        this.netflixUserRepository.deleteById(id);
    }
}
