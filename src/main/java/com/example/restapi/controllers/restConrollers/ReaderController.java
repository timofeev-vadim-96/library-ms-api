package com.example.restapi.controllers.restConrollers;

import com.example.restapi.models.IssueEntity;
import com.example.restapi.models.ReaderEntity;
import com.example.restapi.services.issue.IssueService;
import com.example.restapi.services.reader.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    private final ReaderService readerService;
    private final IssueService issueService;

    public ReaderController(ReaderService readerService, IssueService issueService) {
        this.readerService = readerService;
        this.issueService = issueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderEntity> getById(@PathVariable("id") long id){
        ReaderEntity readerEntity = readerService.findById(id);
        if (readerEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(readerEntity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReaderEntity> add(@RequestBody ReaderEntity inputReaderEntity){
        ReaderEntity returnedReaderEntity = readerService.save(inputReaderEntity);
        return new ResponseEntity<>(returnedReaderEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        readerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<IssueEntity>> readerIssues(@PathVariable("id") long id){
        List<IssueEntity> readerIssueEntities = issueService.getReaderIssues(id);
        if (readerIssueEntities == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(readerIssueEntities, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReaderEntity>> getALl(){
        List<ReaderEntity> readerEntities = readerService.findAll();
        return new ResponseEntity<>(readerEntities, HttpStatus.OK);
    }
}
