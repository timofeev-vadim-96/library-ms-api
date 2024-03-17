package com.example.restapi.services.book;

import com.example.restapi.models.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity findById(long id);

    BookEntity save(BookEntity bookEntity);

    void deleteById(long id);

    List<BookEntity> findAll();
}
