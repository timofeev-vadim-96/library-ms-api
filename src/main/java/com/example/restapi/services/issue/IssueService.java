package com.example.restapi.services.issue;

import com.example.restapi.controllers.dto.IssueRequest;
import com.example.restapi.models.BookEntity;
import com.example.restapi.models.IssueEntity;
import java.util.List;

public interface IssueService {
    IssueEntity findById(long id);

    IssueEntity save(IssueRequest issueRequest);

    void deleteById(long id);

    List<IssueEntity> findAll();


    List<IssueEntity> getReaderIssues(long readerId);
    public List<BookEntity> getReaderBooks(long readerId);

    IssueEntity closeIssue(long issueId);
}
