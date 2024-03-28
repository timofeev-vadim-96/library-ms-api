package com.example.restapi.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@ConfigurationProperties("application.issue")
@Component
public class IssueConf {
    private int maxAllowedBooks;

    @EventListener(ContextRefreshedEvent.class)
    private void postInit(){
        System.out.println("max allowed books in IssueConf-class: " + maxAllowedBooks);
    }
}
