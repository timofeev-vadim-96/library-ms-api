package com.example.restapi.utils;

import com.example.restapi.dao.BookDao;
import com.example.restapi.models.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookDaoInitializer {
    private final BookDao bookDao;

    public BookDaoInitializer(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void init(){
        bookDao.add(new Book("Мастер и Маргарита"));
        bookDao.add(new Book("Песнь льда и пламени"));
        bookDao.add(new Book("Тень и кость"));

        log.info("Инициализация тестовых значений BookDao произведена");
    }
}
