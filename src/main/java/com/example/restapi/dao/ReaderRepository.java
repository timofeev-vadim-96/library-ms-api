package com.example.restapi.dao;

import com.example.restapi.models.appEntities.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReaderRepository extends JpaRepository<ReaderEntity, Long> {
    ReaderEntity findByPhone(String phone);
    List<ReaderEntity> findAllBySecondName(String secondName);
    List<ReaderEntity> findAllByBirthDayAfter(LocalDate date);
}
