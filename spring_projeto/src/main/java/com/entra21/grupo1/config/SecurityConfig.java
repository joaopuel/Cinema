package com.entra21.grupo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic()
                .and()
                .authorizeHttpRequests((req) -> req
                        .antMatchers("/cadastro").permitAll()
                        .antMatchers(HttpMethod.POST, "/usuarios/criarusuario").permitAll()
                        .antMatchers(HttpMethod.POST, "/usuarios/meusdados").permitAll()
                        .antMatchers(HttpMethod.GET, "/filmes").permitAll()
                        .antMatchers(HttpMethod.GET, "/filmes/{nome}").permitAll()
                        .antMatchers(HttpMethod.GET, "/generos").permitAll()
                        .antMatchers(HttpMethod.GET, "/cinemas").permitAll()
                        .antMatchers(HttpMethod.GET, "/sessoes").permitAll()
                        .antMatchers(HttpMethod.GET, "/sessoes/{id}").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}
