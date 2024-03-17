package com.example.restapi.utils;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DaoInitializer {
    private BookDaoInitializer bookDaoInitializer;
    private ReaderDaoInitializer readerDaoInitializer;
    private IssueDaoInitializer issueDaoInitializer;

    @EventListener(ContextRefreshedEvent.class)
    public void init(){
        bookDaoInitializer.init();
        readerDaoInitializer.init();
        issueDaoInitializer.init();
    }
}
