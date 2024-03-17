package com.example.restapi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class DaoAbstract<T> {

    protected List<T> entities;

    public DaoAbstract() {
        entities = new CopyOnWriteArrayList<>();
    }

    public abstract T add(T entity);

    public abstract T getById(long id);

    public abstract boolean removeById(long id);

    public List<T> getAll(){
        return entities;
    }
}
