package ru.gb.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssueRequest {
    private long bookId;
    private long readerId;
}
