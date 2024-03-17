package com.example.restapi.controllers;

import com.example.restapi.models.Book;
import com.example.restapi.services.ServiceGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final ServiceGeneral<Book> service;

    public BookController(ServiceGeneral<Book> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id){
        Book book = service.getById(id);
        if (book == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBookById(@PathVariable("id") long id){
        boolean isSuccessfully = service.removeById(id);
        if (!isSuccessfully) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book inputBook) {
        Book book = service.add(inputBook);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getALl(){
        List<Book> books = service.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
