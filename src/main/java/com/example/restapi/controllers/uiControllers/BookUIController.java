package com.example.restapi.controllers.uiControllers;

import com.example.restapi.models.appEntities.BookEntity;
import com.example.restapi.services.book.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ui/book")
public class BookUIController {
    private BookService bookService;

    public BookUIController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooks(Model model){
        List<BookEntity> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }
}
