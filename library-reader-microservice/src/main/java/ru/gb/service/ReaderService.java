package ru.gb.service;


import ru.gb.model.BookEntity;
import ru.gb.model.ReaderEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReaderService {
    ReaderEntity findById(long id);

    ReaderEntity save(ReaderEntity readerEntity);

    boolean deleteById(long id);

    List<ReaderEntity> findAll();
    ReaderEntity findByPhone(String phone);
    List<ReaderEntity> findAllBySecondName(String secondName);
    List<ReaderEntity> findAllByBirthDayAfter(LocalDate inputDate);
    List<BookEntity> getReadersBooks(long readerId);
}
