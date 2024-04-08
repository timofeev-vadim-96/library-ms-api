package ru.gb.service;


import ru.gb.model.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity findById(long id);

    BookEntity save(BookEntity bookEntity);

    boolean deleteById(long id);

    List<BookEntity> findAll();
    BookEntity findByName(String bookName);
}
