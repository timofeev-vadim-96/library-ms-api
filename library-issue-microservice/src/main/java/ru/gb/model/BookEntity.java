package ru.gb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
@Schema(name = "Книга")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор")
    private long id;
    @Schema(name = "Название книги")
    private String name;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
    @Column(name = "written_date")
    private LocalDate writtenDate;

    public BookEntity(String name, AuthorEntity authorEntity, LocalDate writtenDate) {
        this.name = name;
        this.authorEntity = authorEntity;
        this.writtenDate = writtenDate;
    }

    @Override
    public boolean equals(Object obj) {
        BookEntity book = (BookEntity) obj;
        return book.id == this.id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
