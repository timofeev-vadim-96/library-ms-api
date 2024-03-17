package com.example.restapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
public class Book {
    private long id;
    private String name;

    private static AtomicLong idCounter = new AtomicLong(0);

    public Book(String name) {
        this.name = name;
        id = idCounter.getAndIncrement();
    }
}
