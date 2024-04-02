package com.example.restapi.services.book;

import com.example.restapi.models.appEntities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity findById(long id);

    BookEntity save(BookEntity bookEntity);

    boolean deleteById(long id);

    List<BookEntity> findAll();
    BookEntity findByName(String bookName);
}
