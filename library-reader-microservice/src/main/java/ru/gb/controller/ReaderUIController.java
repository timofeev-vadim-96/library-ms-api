package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.model.BookEntity;
import ru.gb.model.ReaderEntity;
import ru.gb.service.IssueProvider;
import ru.gb.service.ReaderService;

import java.util.List;

@Controller
@RequestMapping("/ui/reader")
@RequiredArgsConstructor
public class ReaderUIController {
    private final ReaderService readerService;

    @GetMapping
    public String getReaders(Model model){
        List<ReaderEntity> readers = readerService.findAll();
        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("/{id}")
    public String getReaderBooks(@PathVariable("id") long id, Model model){
        ReaderEntity reader = readerService.findById(id);
        if (reader == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не удалось найти пользователя с id=" + id);
        }
        List<BookEntity> books = readerService.getReadersBooks(id);
        model.addAttribute("reader", reader);
        model.addAttribute("books", books);
        return "readerBooks";
    }
}
