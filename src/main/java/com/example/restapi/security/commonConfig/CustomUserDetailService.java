package com.example.restapi.security.commonConfig;

import com.example.restapi.dao.CustomUserDao;
import com.example.restapi.models.security.CustomUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Profile("security_common")
public class CustomUserDetailService implements UserDetailsService {
    private final CustomUserDao dao;

    /**
     * Определяет, есть ли такой пользователь в БД и какие у него права
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        CustomUserEntity user = dao.findByLogin(login).orElseThrow(()->
                new UsernameNotFoundException(String.format("пользователь с логином %s не найден.", login)));
        return new User(user.getLogin(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
