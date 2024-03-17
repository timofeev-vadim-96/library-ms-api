package com.example.restapi.utils;

import com.example.restapi.dao.ReaderDao;
import com.example.restapi.models.Reader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class ReaderDaoInitializer {
    private final ReaderDao readerDao;

    public ReaderDaoInitializer(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    public void init(){
        readerDao.add(new Reader("Иван", "Иванович", "89991231321", "vanya@ya.ru",
                LocalDate.of(1980, 10, 1)));

        readerDao.add(new Reader("Петр", "Петрович", "82342452325", "petya@ya.ru",
                LocalDate.of(1968, 4, 3)));

        readerDao.add(new Reader("Вася", "Васин", "89928591348", "vasya@ya.ru",
                LocalDate.of(1999, 5, 2)));

        log.info("Инициализация тестовых значений ReaderDao произведена");
    }
}
