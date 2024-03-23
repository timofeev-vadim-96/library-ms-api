package com.example.restapi.services.book;

import com.example.restapi.dao.BookRepository;
import com.example.restapi.models.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository dao;

    public BookServiceImpl(BookRepository dao) {
        this.dao = dao;
    }

    @Override
    public BookEntity findById(long id){
        return dao.findById(id).orElse(null);
    }

    @Override
    public BookEntity save(BookEntity bookEntity){
        return dao.save(new BookEntity(bookEntity.getName()));
    }

    @Override
    public void deleteById(long id){
        dao.deleteById(id);
    }

    @Override
    public List<BookEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public BookEntity findByName(String bookName) {
        return dao.findBookEntitiesByNameContaining(bookName);
    }
}
