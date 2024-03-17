package com.example.restapi.dao;

import com.example.restapi.models.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends DaoAbstract<Book>{

    @Override
    public Book add(Book book){
        super.entities.add(book);
        return book;
    }

    @Override
    public Book getById(long id){
        return super.entities.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removeById(long id){
        return super.entities.removeIf(book -> book.getId() == id);
    }
}
