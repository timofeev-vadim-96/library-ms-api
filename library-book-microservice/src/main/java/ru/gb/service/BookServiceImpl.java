package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.dao.AuthorRepository;
import ru.gb.dao.BookRepository;
import ru.gb.model.AuthorEntity;
import ru.gb.model.BookEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository dao;
    private final AuthorRepository authorDao;

    @Override
    public BookEntity findById(long id){
        return dao.findById(id).orElse(null);
    }

    @Override
    public BookEntity save(BookEntity bookEntity){
        AuthorEntity author = bookEntity.getAuthorEntity();
        if (authorDao.findFirstByFirstNameAndLastName(author.getFirstName(), author.getLastName()) == null){
            AuthorEntity returnedAuthor = authorDao.save(author);
            bookEntity.setAuthorEntity(returnedAuthor);
        } else {
            AuthorEntity returnedAuthor = authorDao
                    .findFirstByFirstNameAndLastName(author.getFirstName(), author.getLastName());
            author.setId(returnedAuthor.getId());
        }
        return dao.save(bookEntity);
    }

    @Override
    public boolean deleteById(long id){
        BookEntity book = findById(id);
        if (book == null) return false;
        else {
            dao.deleteById(id);
            return true;
        }
    }

    @Override
    public List<BookEntity> findAll() {
        return dao.findAll();
    }

    @Override
    public BookEntity findByName(String bookName) {
        return dao.findBookEntitiesByNameContaining(bookName);
    }
}
