package br.org.sesisenai.estoqueapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Desabilita proteção CSRF (necessário para APIs REST que usam token JWT)
                .csrf(AbstractHttpConfigurer::disable)
                // 2. Define a política de sessão como STATELESS (Não guarda sessão na memoria,tudo é via token)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/invite/accept").permitAll()

                        // Libera o Swagger/OpenAPI
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passworEncoder() {
        return new BCryptPasswordEncoder();
    }
}
