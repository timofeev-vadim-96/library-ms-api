package com.example.restapi.dao;

import com.example.restapi.models.Reader;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderDao extends DaoAbstract<Reader>{

    @Override
    public Reader add(Reader reader){
        super.entities.add(reader);
        return reader;
    }

    @Override
    public Reader getById(long id){
        return super.entities.stream()
                .filter(reader -> reader.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removeById(long id){
        return super.entities.removeIf(reader -> reader.getId() == id);
    }
}
