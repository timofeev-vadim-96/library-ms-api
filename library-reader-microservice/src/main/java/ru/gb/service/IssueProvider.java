package ru.gb.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.model.IssueEntity;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class IssueProvider {
    private final EurekaClient eurekaClient;

    public List<IssueEntity> getReaderIssues(long readerId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getIssueServiceIp() + "issue/readersIssues/" + readerId;

        ResponseEntity<List<IssueEntity>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {

            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    private String getIssueServiceIp(){
        Application application = eurekaClient.getApplication("ISSUE-MICROSERVICE");
        List<InstanceInfo> instanceInfos = application.getInstances();

        Random random = new Random();
        InstanceInfo randomInstance = instanceInfos.get(random.nextInt(instanceInfos.size()));
        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort() + "/";
    }
}
