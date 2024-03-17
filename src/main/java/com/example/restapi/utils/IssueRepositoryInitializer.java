package com.example.restapi.utils;

import com.example.restapi.dao.IssueRepository;
import com.example.restapi.models.IssueEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class IssueRepositoryInitializer {
    private final IssueRepository issueDao;

    public void init(){
        if (issueDao.findAll().stream().count() == 0) {
            IssueEntity firstIssue = new IssueEntity(0L, 1L);
            firstIssue.setIssueAt();
            IssueEntity secondIssue = new IssueEntity(1L, 1L);
            secondIssue.setIssueAt();
            IssueEntity thirdIssue = new IssueEntity(2L, 2L);
            thirdIssue.setIssueAt();

            issueDao.save(firstIssue);
            issueDao.save(secondIssue);
            issueDao.save(thirdIssue);
        }

        log.info("Инициализация тестовых значений кейсов по взятию книг произведена");
    }
}
