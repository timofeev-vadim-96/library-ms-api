package com.example.restapi.dao;

import com.example.restapi.models.appEntities.BookEntity;
import com.example.restapi.models.security.CustomUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserDao extends JpaRepository<CustomUserEntity, Long> {
    Optional<CustomUserEntity> findByLogin(String login);
}
