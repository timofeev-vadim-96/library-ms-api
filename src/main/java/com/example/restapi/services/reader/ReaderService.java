package com.example.restapi.services.reader;

import com.example.restapi.models.ReaderEntity;

import java.util.List;

public interface ReaderService {
    ReaderEntity findById(long id);

    ReaderEntity save(ReaderEntity readerEntity);

    void deleteById(long id);

    List<ReaderEntity> findAll();
}
