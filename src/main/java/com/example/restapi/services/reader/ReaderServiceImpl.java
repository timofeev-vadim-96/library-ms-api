package com.example.restapi.services.reader;

import com.example.restapi.dao.ReaderRepository;
import com.example.restapi.models.ReaderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository dao;

    public ReaderServiceImpl(ReaderRepository dao) {
        this.dao = dao;
    }

    @Override
    public ReaderEntity findById(long id){
        return dao.findById(id).orElse(null);
    }

    @Override
    public ReaderEntity save(ReaderEntity readerEntity){
        return dao.save(readerEntity);
    }

    @Override
    public void deleteById(long id){
        dao.deleteById(id);
    }

    @Override
    public List<ReaderEntity> findAll() {
        return dao.findAll();
    }
}
