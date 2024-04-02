package com.example.restapi.security.commonConfig;

import com.example.restapi.dao.CustomUserDao;
import com.example.restapi.models.security.CustomUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import javax.security.sasl.AuthenticationException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Profile("security_common")
public class WebSecurityConfig{
    private CustomUserDao dao;

    /**
     * Бин, позволяющий разграничить доступ в зависимости от прав
     * @param httpSecurity - такой бин уже существует под капотом спринга
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(registry-> registry
                        .requestMatchers("/ui/issue/**").hasAuthority("manager")
                        .requestMatchers("/ui/reader/**", "/ui/issue/**").hasAuthority("admin") //конкретная роль
                        .requestMatchers("/ui/book/**").authenticated() //любой авторизованный
                        .requestMatchers("/book/**", "/issue/**", "/reader/**").permitAll() //доступ всем
                        .anyRequest().denyAll() //для всех остальных ресурсов - запрет всем (необязательно)
                )
                .formLogin(Customizer.withDefaults()) //если не авторизовались по фильтрам выше - выдать форму авторизации
                .build();
    }
}
