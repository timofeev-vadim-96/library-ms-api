package com.example.restapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
public class Issue {
    private long id;
    private long bookId;
    private long readerId;
    private LocalDateTime issueAt;
    private LocalDateTime returnedAt;

    static AtomicLong idCounter = new AtomicLong(0);

    public Issue(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    public void setId() {
        id = idCounter.getAndIncrement();
    }

    public void setIssueAt() {
        issueAt = LocalDateTime.now();
    }

    public void setReturnedAt() {
        returnedAt = LocalDateTime.now();
    }
}
