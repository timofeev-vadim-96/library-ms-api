package com.example.restapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "issues")
public class IssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_id")
    private long bookId;
    @Column(name = "reader_id")
    private long readerId;
    @Column(name = "issue_at")
    private LocalDateTime issueAt;
    @Column(name = "returned_at")
    private LocalDateTime returnedAt;


    public IssueEntity(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    public void setIssueAt() {
        issueAt = LocalDateTime.now();
    }

    public void setReturnedAt() {
        returnedAt = LocalDateTime.now();
    }
}
