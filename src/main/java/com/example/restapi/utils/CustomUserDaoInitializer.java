package com.example.restapi.utils;

import com.example.restapi.dao.CustomUserDao;
import com.example.restapi.models.security.CustomUserEntity;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDaoInitializer {
    private CustomUserDao userDao;

    public CustomUserDaoInitializer(CustomUserDao userDao) {
        this.userDao = userDao;
    }

    @EventListener(ContextRefreshedEvent.class)
    private void init(){
        if (userDao.findAll().isEmpty()) {
            CustomUserEntity admin = new CustomUserEntity("admin", "admin", "admin");
            CustomUserEntity manager = new CustomUserEntity("manager", "manager", "manager");
            CustomUserEntity user = new CustomUserEntity("user", "user", "user");
            userDao.save(admin);
            userDao.save(manager);
            userDao.save(user);
        }
    }
}
