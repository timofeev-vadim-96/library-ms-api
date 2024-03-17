package com.example.restapi.services.book;

import com.example.restapi.dao.BookDao;
import com.example.restapi.dao.DaoAbstract;
import com.example.restapi.models.Book;
import com.example.restapi.services.ServiceGeneral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements ServiceGeneral<Book> {
    private final DaoAbstract<Book> dao;

    public BookService(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Book getById(long id){
        return dao.getById(id);
    }

    @Override
    public Book add(Book book){
        return dao.add(new Book(book.getName()));
    }

    @Override
    public boolean removeById(long id){
        return dao.removeById(id);
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }
}
