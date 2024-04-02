package com.example.restapi.services.reader;

import com.example.restapi.models.appEntities.ReaderEntity;

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
}
