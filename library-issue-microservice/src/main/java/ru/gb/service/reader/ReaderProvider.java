package ru.gb.service.reader;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.model.ReaderEntity;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ReaderProvider {
    private final EurekaClient eurekaClient;

    public ReaderEntity findById(long readerId)  {
        RestTemplate restTemplate = new RestTemplate();
        String url = getReaderServiceIp() + "reader/" + readerId;

        ResponseEntity<ReaderEntity> responseEntity = restTemplate.getForEntity(url, ReaderEntity.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    private String getReaderServiceIp(){
        Application application = eurekaClient.getApplication("READER-MICROSERVICE");
        List<InstanceInfo> instanceInfos = application.getInstances();

        Random random = new Random();
        InstanceInfo randomInstance = instanceInfos.get(random.nextInt(instanceInfos.size()));
        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort() + "/";
    }
}
