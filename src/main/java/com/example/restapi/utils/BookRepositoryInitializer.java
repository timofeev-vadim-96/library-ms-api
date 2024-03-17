package com.example.restapi.utils;

import com.example.restapi.dao.BookRepository;
import com.example.restapi.models.BookEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookRepositoryInitializer {
    private final BookRepository bookDao;

    public BookRepositoryInitializer(BookRepository bookDao) {
        this.bookDao = bookDao;
    }

    public void init(){
        if (bookDao.findAll().stream().count() == 0) {
            bookDao.save(new BookEntity("Мастер и Маргарита"));
            bookDao.save(new BookEntity("Песнь льда и пламени"));
            bookDao.save(new BookEntity("Тень и кость"));

            log.info("Инициализация тестовых значений книг произведена");
        }
    }
}
