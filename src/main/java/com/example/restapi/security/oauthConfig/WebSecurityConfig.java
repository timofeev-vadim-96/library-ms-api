package com.example.restapi.security.oauthConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@Profile("security_oauth")
public class WebSecurityConfig{

    /**
     * Бин, позволяющий разграничить доступ в зависимости от прав
     * @param httpSecurity - такой бин уже существует под капотом спринга
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(source -> {
            Map<String, Object> claim = source.getClaim("realm_access"); //достаем заголовок
            List<String> roles = (List<String>) claim.get("roles"); //достаем роли из заголовка
            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });

        return httpSecurity
                .authorizeHttpRequests(registry-> registry
                        .requestMatchers("/ui/issue/**").hasAnyAuthority("manager", "admin")
                        .requestMatchers("/ui/reader/**").hasAuthority("admin") //конкретная роль
                        .requestMatchers("/ui/book/**").authenticated() //любой авторизованный
                        .requestMatchers("/book/**", "/issue/**", "/reader/**").permitAll() //доступ всем
                        .anyRequest().denyAll() //для всех остальных ресурсов - запрет всем (необязательно)
                )

                .oauth2ResourceServer(configurer->configurer
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(converter)))
                .build();
    }
}
