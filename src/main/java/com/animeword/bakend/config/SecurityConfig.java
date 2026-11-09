package com.animeword.bakend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para que Postman y React puedan hacer POST/PUT/DELETE sin tokens extraños
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite el libre acceso a todos tus endpoints (/api/v1/users/..., /api/v1/roles, etc.)
            );
        return http.build();
    }

}
