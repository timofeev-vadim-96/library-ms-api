package com.example.restapi.security.commonConfig;

import org.springframework.context.annotation.Profile;
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
        return String.valueOf(rawPassword);
    }

    /**
     * Сравнение шифрованного пароля с паролем из БД для АВТОРИЗАЦИИ
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
