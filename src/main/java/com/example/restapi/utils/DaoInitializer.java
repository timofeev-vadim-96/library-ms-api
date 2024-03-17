package com.example.restapi.utils;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DaoInitializer {
    private BookRepositoryInitializer bookRepositoryInitializer;
    private ReaderRepositoryInitializer readerRepositoryInitializer;
    private IssueRepositoryInitializer issueRepositoryInitializer;

    @EventListener(ContextRefreshedEvent.class)
    public void init(){
        bookRepositoryInitializer.init();
        readerRepositoryInitializer.init();
        issueRepositoryInitializer.init();
    }
}
