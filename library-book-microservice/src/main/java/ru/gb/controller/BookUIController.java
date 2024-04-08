package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.model.BookEntity;
import ru.gb.service.BookService;

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
