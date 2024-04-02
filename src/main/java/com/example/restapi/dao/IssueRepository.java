package com.example.restapi.dao;

import com.example.restapi.models.appEntities.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    List<IssueEntity> findAllByIssueAtBetween(LocalDateTime from, LocalDateTime to);
}
