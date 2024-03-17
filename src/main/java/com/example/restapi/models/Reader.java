package com.example.restapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
public class Reader {
    private long id;
    private String firstName;
    private String secondName;
    private String phone;
    private String email;
    private LocalDate birthDay;

    private static AtomicLong idCounter = new AtomicLong(0);

    public Reader(String firstName, String secondName, String phone, String email, LocalDate birthDay) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.birthDay = birthDay;
        id = idCounter.getAndIncrement();
    }

    public void setId() {
        id = idCounter.getAndIncrement();
    }
}
