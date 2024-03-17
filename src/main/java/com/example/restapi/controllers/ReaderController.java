package com.example.restapi.controllers;

import com.example.restapi.models.Issue;
import com.example.restapi.models.Reader;
import com.example.restapi.services.issue.ReaderIssuesService;
import com.example.restapi.services.ServiceGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    private final ServiceGeneral<Reader> readerService;
    private final ReaderIssuesService issueService;

    public ReaderController(ServiceGeneral<Reader> readerService, ReaderIssuesService issueService) {
        this.readerService = readerService;
        this.issueService = issueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getById(@PathVariable("id") long id){
        Reader reader = readerService.getById(id);
        if (reader == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reader> add(@RequestBody Reader inputReader){
        Reader returnedReader = readerService.add(inputReader);
        return new ResponseEntity<>(returnedReader, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        boolean isSuccessfully = readerService.removeById(id);
        if (!isSuccessfully) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> readerIssues(@PathVariable("id") long id){
        List<Issue> readerIssues = issueService.getReaderIssues(id);
        if (readerIssues == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(readerIssues, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reader>> getALl(){
        List<Reader> readers = readerService.getAll();
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }
}
