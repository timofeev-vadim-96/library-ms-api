package com.example.restapi.controllers;

import com.example.restapi.models.BookEntity;
import com.example.restapi.services.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable("id") long id){
        BookEntity bookEntity = service.findById(id);
        if (bookEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(bookEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBookById(@PathVariable("id") long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity inputBookEntity) {
        BookEntity bookEntity = service.save(inputBookEntity);
        return new ResponseEntity<>(bookEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getALl(){
        List<BookEntity> bookEntities = service.findAll();
        return new ResponseEntity<>(bookEntities, HttpStatus.OK);
    }
}
