package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.dao.ReaderRepository;
import ru.gb.model.BookEntity;
import ru.gb.model.IssueEntity;
import ru.gb.model.ReaderEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository dao;
    private final IssueProvider issueProvider;

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

    @Override
    public List<BookEntity> getReadersBooks(long readerId) {
        return issueProvider.getReaderIssues(readerId).stream().map(IssueEntity::getBook).collect(Collectors.toList());
    }
}
