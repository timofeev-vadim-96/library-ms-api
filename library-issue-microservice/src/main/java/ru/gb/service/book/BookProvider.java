package ru.gb.service.book;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.model.BookEntity;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookProvider {
    private final EurekaClient eurekaClient;

    public BookEntity findById(long bookId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getBookServiceIp() + "book/" + bookId;

        System.out.println("BOOK SERVICE: " + url);

        ResponseEntity<BookEntity> responseEntity = restTemplate.getForEntity(url, BookEntity.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    private String getBookServiceIp(){
        Application application = eurekaClient.getApplication("BOOK-MICROSERVICE");
        List<InstanceInfo> instanceInfos = application.getInstances();

        Random random = new Random();
        InstanceInfo randomInstance = instanceInfos.get(random.nextInt(instanceInfos.size()));
        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort() + "/";
    }
}
