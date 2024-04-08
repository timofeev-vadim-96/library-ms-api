package ru.gb.service.reader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.model.ReaderEntity;

@Service
public class ReaderProvider {

    public ReaderEntity findById(long readerId)  {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/reader/" + readerId; // URL вашего контроллера

        System.out.println("ПЕРЕД ВОЗВРАЩЕНИЕМ СУЩНОСТИ");
        ResponseEntity<ReaderEntity> responseEntity = restTemplate.getForEntity(url, ReaderEntity.class);
        System.out.println("ВЕРНУЛ СУЩНОСТЬ " + responseEntity);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
}
