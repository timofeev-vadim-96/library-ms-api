package com.example.restapi.services.issue;

import com.example.restapi.dao.DaoAbstract;
import com.example.restapi.exceptions.DebtorException;
import com.example.restapi.exceptions.TheBookIsBusy;
import com.example.restapi.models.Book;
import com.example.restapi.models.Issue;
import com.example.restapi.models.Reader;
import com.example.restapi.services.book.BookService;
import com.example.restapi.services.reader.ReaderService;
import com.example.restapi.services.ServiceGeneral;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class IssueService implements ServiceGeneral<Issue>, ReaderIssuesService {
    private final DaoAbstract<Issue> issueDao;
    private final ReaderService readerService;
    private final BookService bookService;
    private Environment environment;

    @Override
    public Issue getById(long id){
        return issueDao.getById(id);
    }

    @Override
    public Issue add(Issue issue) {
        Reader reader = readerService.getById(issue.getReaderId());
        Book book = bookService.getById(issue.getBookId());

        if (reader == null) {
            log.info("Запрос на добавление факта выдачи ссылается на не существующего читателя. readerId={}",
                    issue.getReaderId());
            return null;
        }

        else if (book == null) {
            log.info("Запрос на добавление факта выдачи ссылается на не существующую книгу. readerId={}",
                    issue.getBookId());
            return null;
        }

        else if (isTheReaderInDebt(reader)){
            throw new DebtorException(String.format("Читатель с id=%d является должником.", reader.getId()));
        }

        else if (isBookBusy(book)){
            throw new TheBookIsBusy(String.format("Книга с id=%d находится на руках у другого читателя.", book.getId()));
        }

        issue.setIssueAt();
        issue.setId();
        return issueDao.add(issue);
    }

    @Override
    public boolean removeById(long id) {
        return issueDao.removeById(id);
    }

    @Override
    public List<Issue> getAll() {
        return issueDao.getAll();
    }

    private boolean isTheReaderInDebt(Reader reader){
        long quantityBooksOnHand = issueDao.getAll().stream()
                .filter(issue -> issue.getReaderId() == reader.getId() && issue.getReturnedAt() == null)
                .count();

        Integer maxAllowedBooks = environment.getProperty("${application.issue.max-allowed-books}", Integer.class);
        if (maxAllowedBooks == null) maxAllowedBooks = 1;
        return quantityBooksOnHand > maxAllowedBooks;
    }

    @Override
    public List<Issue> getReaderIssues(long readerId) {
        Reader reader = readerService.getById(readerId);
        if (reader == null) {
            log.info("Запрос на получения списка выдач книг на руки ссылается на не существующего читателя. readerId={}",
                    readerId);
            return null;
        }
        return issueDao.getAll().stream()
                .filter(issue -> issue.getReaderId() == readerId && issue.getReturnedAt() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Issue closeIssue(long issueId) {
        Issue issue = issueDao.getById(issueId);
        if (issue == null){
            log.info("Запрос на закрытие факта выдачи ссылается на несуществующий факт выдачи. issueId={}", issueId);
            return null;
        }
        issue.setReturnedAt();
        return issue;
    }

    private boolean isBookBusy(Book book){
        Issue issue = issueDao.getAll().stream()
                .filter(iss -> iss.getBookId() == book.getId())
                .findFirst()
                .orElse(null);
        return issue != null;
    }
}
