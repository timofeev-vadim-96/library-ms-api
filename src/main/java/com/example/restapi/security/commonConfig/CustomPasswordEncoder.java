package com.example.restapi.security.commonConfig;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("security_common")
public class CustomPasswordEncoder implements PasswordEncoder {

    /**
     * Метод шифрования данных в тот вид, в котором будут храниться данные
     */
    @Override
    public String encode(CharSequence rawPassword) {
        //шифруем данные
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Сравнение шифрованного пароля с паролем из БД для АВТОРИЗАЦИИ
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
