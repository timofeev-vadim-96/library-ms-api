package com.example.restapi.services.reader;

import com.example.restapi.dao.ReaderRepository;
import com.example.restapi.models.appEntities.ReaderEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[0-2])[/](19[0-9][0-9]|20[0-9][0-9])";
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
    public boolean deleteById(long id){
        ReaderEntity reader = findById(id);
        if (reader == null) return false;
        else {
            dao.deleteById(id);
            return true;
        }
    }

    @Override
    public List<ReaderEntity> findAll() {
        return dao.findAll();
    }
    @Override
    public ReaderEntity findByPhone(String phone){
        return dao.findByPhone(phone);
    }
    @Override
    public List<ReaderEntity> findAllBySecondName(String secondName){
        return dao.findAllBySecondName(secondName);
    }

    @Override
    public List<ReaderEntity> findAllByBirthDayAfter(LocalDate date){
        return dao.findAllByBirthDayAfter(date);
    }
}
