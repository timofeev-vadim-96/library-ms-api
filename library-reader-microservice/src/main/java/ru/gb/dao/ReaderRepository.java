package ru.gb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.model.ReaderEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReaderRepository extends JpaRepository<ReaderEntity, Long> {
    ReaderEntity findByPhone(String phone);
    List<ReaderEntity> findAllBySecondName(String secondName);
    List<ReaderEntity> findAllByBirthDayAfter(LocalDate date);
}
