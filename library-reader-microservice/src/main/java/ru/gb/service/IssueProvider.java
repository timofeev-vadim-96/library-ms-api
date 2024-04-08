package ru.gb.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.model.IssueEntity;
import java.util.List;

@Component
public class IssueProvider {

    public List<IssueEntity> getReaderIssues(long readerId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8085/issue/readersIssues/" + readerId;

        ResponseEntity<List<IssueEntity>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<IssueEntity> readersIssues = responseEntity.getBody();

            //todo log
            System.out.println(readersIssues);

            return readersIssues;
        } else {
            return null;
        }
    }
}
