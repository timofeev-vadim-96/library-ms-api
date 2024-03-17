package com.example.restapi.utils.events;

import org.springframework.context.ApplicationEvent;

public class DaoInitializationEvent extends ApplicationEvent {
    public DaoInitializationEvent(Object source) {
        super(source);
    }
}
