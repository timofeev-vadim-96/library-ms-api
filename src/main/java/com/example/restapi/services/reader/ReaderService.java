package com.example.restapi.services.reader;

import com.example.restapi.dao.DaoAbstract;
import com.example.restapi.models.Reader;
import com.example.restapi.services.ServiceGeneral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService implements ServiceGeneral<Reader> {
    private final DaoAbstract<Reader> dao;

    public ReaderService(DaoAbstract<Reader> dao) {
        this.dao = dao;
    }

    @Override
    public Reader getById(long id){
        return dao.getById(id);
    }

    @Override
    public Reader add(Reader reader){
        reader.setId();
        return dao.add(reader);
    }

    @Override
    public boolean removeById(long id){
        return dao.removeById(id);
    }

    @Override
    public List<Reader> getAll() {
        return dao.getAll();
    }
}
