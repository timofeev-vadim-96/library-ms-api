package com.example.restapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public BookEntity(String name) {
        this.name = name;
    }
}
