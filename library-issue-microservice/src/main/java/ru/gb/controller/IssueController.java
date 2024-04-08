package ru.gb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.controller.dto.IssueRequest;
import ru.gb.exceptions.DebtorException;
import ru.gb.exceptions.TheBookIsBusy;
import ru.gb.model.BookEntity;
import ru.gb.model.IssueEntity;
import ru.gb.service.issue.IssueService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {
    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @Operation(summary = "issue a book", description = "Выдать книгу, сохранив случай выдачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT")
    })
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

    @Operation(summary = "get issue by id", description = "Получить случай выдачи по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<IssueEntity> getIssue(@PathVariable("id") long id) {
        IssueEntity issueEntity = service.findById(id);
        if (issueEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(issueEntity, HttpStatus.OK);
    }

    @Operation(summary = "close issue", description = "Закрыть факт выдачи по возврату книги читателем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
    })
    @PutMapping("/{issueId}")
    public ResponseEntity<IssueEntity> closeIssue(@PathVariable("issueId") long issueId){
        IssueEntity issueEntity = service.closeIssue(issueId);
        if (issueEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(issueEntity, HttpStatus.OK);
    }

    @Operation(summary = "get all issues", description = "Получить все случаи выдач книг читателям")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<List<IssueEntity>> getALl(){
        List<IssueEntity> issueEntities = service.findAll();
        return new ResponseEntity<>(issueEntities, HttpStatus.OK);
    }

    @Operation(summary = "get issues by the time interval",
            description = "Получить все случаи выдачи в определенном интервале дат")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(params = {"from", "to"})
    public ResponseEntity<List<IssueEntity>> findAllByIssueAtBetween
            (@RequestParam("from") LocalDateTime from,
             @RequestParam("to") LocalDateTime to){
        return new ResponseEntity<>(service.findAllByIssueAtBetween(from, to), HttpStatus.OK);
    }

    @Operation(summary = "reader's books", description = "Получить список книг, находящихся на руках у читателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
    })
    @GetMapping("readersIssues/{readerId}")
    public ResponseEntity<List<IssueEntity>> findReadersIssuesByReaderId(@PathVariable long readerId){
        List<IssueEntity> readersBooks = service.getReaderIssues(readerId);
        if (readersBooks == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(readersBooks, HttpStatus.OK);
    }
}
