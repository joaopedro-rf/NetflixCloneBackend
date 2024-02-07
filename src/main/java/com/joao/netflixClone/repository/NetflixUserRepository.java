package com.joao.netflixClone.repository;


import com.joao.netflixClone.model.NetflixUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface NetflixUserRepository extends JpaRepository<NetflixUser, Long> {
    NetflixUser findByEmail(String email);

    NetflixUser findByUsername(String username);
}
