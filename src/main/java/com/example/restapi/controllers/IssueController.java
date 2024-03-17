package com.example.restapi.controllers;

import com.example.restapi.controllers.dto.IssueRequest;
import com.example.restapi.exceptions.DebtorException;
import com.example.restapi.exceptions.TheBookIsBusy;
import com.example.restapi.models.IssueEntity;
import com.example.restapi.services.issue.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {
    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IssueEntity> bookIssuance(@RequestBody IssueRequest issueRequest) {
        try {
            IssueEntity issueEntity = service.save(issueRequest);
            if (issueEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<>(issueEntity, HttpStatus.CREATED);
        } catch (DebtorException | TheBookIsBusy e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueEntity> getIssue(@PathVariable("id") long id) {
        IssueEntity issueEntity = service.findById(id);
        if (issueEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(issueEntity, HttpStatus.OK);
    }

    @PutMapping("/{issueId}")
    public ResponseEntity<IssueEntity> closeIssue(@PathVariable("issueId") long issueId){
        IssueEntity issueEntity = service.closeIssue(issueId);
        if (issueEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(issueEntity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IssueEntity>> getALl(){
        List<IssueEntity> issueEntities = service.findAll();
        return new ResponseEntity<>(issueEntities, HttpStatus.OK);
    }
}
