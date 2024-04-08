package ru.gb.model;

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
    @OneToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private ReaderEntity reader;
    @Column(name = "issue_at")
    private LocalDateTime issueAt;
    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    public IssueEntity(BookEntity book, ReaderEntity reader) {
        this.book = book;
        this.reader = reader;
    }

    public void setIssueAt() {
        issueAt = LocalDateTime.now();
    }

    public void setReturnedAt() {
        returnedAt = LocalDateTime.now();
    }
}
