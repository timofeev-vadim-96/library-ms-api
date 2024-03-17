package com.example.restapi.services;

import java.util.List;

public interface ServiceGeneral<T>{
    T getById(long id);

    T add(T t);

    public boolean removeById(long id);
    List<T> getAll();
}
