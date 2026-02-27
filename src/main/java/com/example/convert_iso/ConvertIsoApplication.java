package com.example.convert_iso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class ConvertIsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvertIsoApplication.class, args);
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders(
                        "Content-Disposition"
                )
                .allowCredentials(true)
                .maxAge(3600);
    }
}
