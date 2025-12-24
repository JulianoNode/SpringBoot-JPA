package com.Strart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // 🔓 RECURSOS PÚBLICOS (PRIMEIRO)
                .requestMatchers(
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/imagens/**",
                    "/login"
                ).permitAll()
                // 🔒 ROTAS PROTEGIDAS
                .requestMatchers("/imagens/**").permitAll()
                .requestMatchers("/produtos/editar/**").hasRole("ADMIN")
                .requestMatchers("/produtos/excluir/**").hasRole("ADMIN")
                .requestMatchers("/produtos/**").authenticated()
                // 🔒 QUALQUER OUTRA
                .anyRequest().authenticated()
            )
            .httpBasic(); // login básico no browser

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService users(PasswordEncoder encoder) {

        UserDetails admin = User.withUsername("admin")
            .password(encoder.encode("123"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
