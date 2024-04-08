package ru.gb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.model.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findBookEntitiesByNameContaining(String bookName);
}
