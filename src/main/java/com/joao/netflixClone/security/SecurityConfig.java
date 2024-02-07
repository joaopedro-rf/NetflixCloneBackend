package com.joao.netflixClone.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    public SecurityConfig() {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return (SecurityFilterChain)((HttpSecurity)((HttpSecurity)httpSecurity.cors().and()).csrf().disable()).sessionManagement((session) -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }).authorizeHttpRequests((authorize) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)authorize.requestMatchers(new String[]{"api/users/**"})).permitAll().anyRequest()).authenticated();
        }).addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://localhost:8080");
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedHeader("Content-Type");
        configuration.addAllowedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
