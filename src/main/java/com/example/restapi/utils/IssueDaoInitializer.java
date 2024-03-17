package com.example.restapi.utils;

import com.example.restapi.models.Issue;
import com.example.restapi.services.issue.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IssueDaoInitializer {
    IssueService service;

    public IssueDaoInitializer(IssueService service) {
        this.service = service;
    }

    public void init(){
        service.add(new Issue(0,1));
        service.add(new Issue(1,1));
        service.add(new Issue(2, 2));

        log.info("Инициализация тестовых значений IssueDao произведена");
    }
}
