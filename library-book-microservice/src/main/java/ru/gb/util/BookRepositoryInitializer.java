package ru.gb.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.gb.dao.BookRepository;
import ru.gb.model.AuthorEntity;
import ru.gb.model.BookEntity;
import ru.gb.service.BookService;

import java.time.LocalDate;
import java.time.Year;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookRepositoryInitializer {
    private final BookService bookService;

    @EventListener(ContextRefreshedEvent.class)
    public void init(){
        if (bookService.findAll().isEmpty()) {
            bookService.save(new BookEntity(
                    "Мастер и Маргарита",
                    new AuthorEntity("Михаил", "Булгаков"),
                    Year.of(1967)));

            bookService.save(new BookEntity(
                    "Песнь льда и пламени",
                    new AuthorEntity("Джордж Р.Р.", "Мартин"),
                    Year.of(1996)));

            bookService.save(new BookEntity(
                    "Тень и кость",
                    new AuthorEntity("Ли", "Бардуго"),
                    Year.of(2012)));

            log.info("Инициализация тестовых значений книг произведена");
        }
    }
}
