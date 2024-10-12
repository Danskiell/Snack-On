package com.restful.snackapi.security;
import com.auth0.spring.security.api.JwtAuthenticationProvider;
import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.clientId}")
    private String clientId;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(clientId, domain)
                .configure(http)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/snacks/**").authenticated()
                        .anyRequest().permitAll()
                );
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.disable());

        return http.build();
    }


}
