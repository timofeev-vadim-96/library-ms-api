package com.example.restapi.utils;

import com.example.restapi.dao.ReaderRepository;
import com.example.restapi.models.appEntities.ReaderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class ReaderRepositoryInitializer {
    private final ReaderRepository dao;

    public ReaderRepositoryInitializer(ReaderRepository dao) {
        this.dao = dao;
    }

    public void init(){
        if (dao.findAll().isEmpty()) {
            dao.save(new ReaderEntity("Иван", "Иванович", "89991231321", "vanya@ya.ru",
                    LocalDate.of(1980, 10, 1)));

            dao.save(new ReaderEntity("Петр", "Петрович", "82342452325", "petya@ya.ru",
                    LocalDate.of(1968, 4, 3)));

            dao.save(new ReaderEntity("Вася", "Васин", "89928591348", "vasya@ya.ru",
                    LocalDate.of(1999, 5, 2)));
        }

        log.info("Инициализация тестовых значений читателей произведена");
    }
}
