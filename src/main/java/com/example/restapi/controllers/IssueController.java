package com.example.restapi.controllers;

import com.example.restapi.exceptions.DebtorException;
import com.example.restapi.exceptions.TheBookIsBusy;
import com.example.restapi.models.Book;
import com.example.restapi.models.Issue;
import com.example.restapi.services.issue.IssueService;
import com.example.restapi.services.issue.ReaderIssuesService;
import com.example.restapi.services.ServiceGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {
    private final ServiceGeneral<Issue> service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Issue> bookIssuance(@RequestBody Issue inputIssue) {
        try {
            Issue issue = service.add(inputIssue);
            if (issue == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<>(issue, HttpStatus.CREATED);
        } catch (DebtorException | TheBookIsBusy e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable("id") long id) {
        Issue issue = service.getById(id);
        if (issue == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    @PutMapping("/{issueId}")
    public ResponseEntity<Issue> closeIssue(@PathVariable("issueId") long issueId){
        ReaderIssuesService issuesService = (ReaderIssuesService) service;
        Issue issue = issuesService.closeIssue(issueId);
        if (issue == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getALl(){
        List<Issue> issues = service.getAll();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
}
