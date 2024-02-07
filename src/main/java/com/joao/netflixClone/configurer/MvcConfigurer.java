package com.joao.netflixClone.configurer;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfigurer implements WebMvcConfigurer {
    public MvcConfigurer() {
    }
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
        pageHandler.setFallbackPageable(PageRequest.of(0, 5));
        resolvers.add(pageHandler);
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api").allowedOrigins(new String[]{"http://localhost:5173"}).allowedOrigins(new String[]{"http://localhost:8080"}).allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}).allowedHeaders(new String[]{"Content-Type", "Authorization"});
    }
}
