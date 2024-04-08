package ru.gb.service.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.model.BookEntity;

@Service
public class BookProvider {

    public BookEntity findById(long bookId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/book/" + bookId;
        ResponseEntity<BookEntity> responseEntity = restTemplate.getForEntity(url, BookEntity.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
}
