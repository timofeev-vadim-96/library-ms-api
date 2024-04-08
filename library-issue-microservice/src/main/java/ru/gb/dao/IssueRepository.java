package ru.gb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.model.IssueEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    List<IssueEntity> findAllByIssueAtBetween(LocalDateTime from, LocalDateTime to);
}
