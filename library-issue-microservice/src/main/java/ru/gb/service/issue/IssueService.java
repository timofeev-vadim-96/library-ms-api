package ru.gb.service.issue;


import ru.gb.controller.dto.IssueRequest;
import ru.gb.model.BookEntity;
import ru.gb.model.IssueEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IssueService {
    IssueEntity findById(long id);

    IssueEntity save(IssueRequest issueRequest);

    void deleteById(long id);

    List<IssueEntity> findAll();


    List<IssueEntity> getReaderIssues(long readerId);

    IssueEntity closeIssue(long issueId);
    public List<IssueEntity> findAllByIssueAtBetween(LocalDateTime inputFrom, LocalDateTime inputTo);
}
