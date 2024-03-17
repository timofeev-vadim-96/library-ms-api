package com.example.restapi.services.issue;

import com.example.restapi.models.Issue;

import java.util.List;

public interface ReaderIssuesService {
    List<Issue> getReaderIssues(long readerId);
    Issue closeIssue(long issueId);
}
