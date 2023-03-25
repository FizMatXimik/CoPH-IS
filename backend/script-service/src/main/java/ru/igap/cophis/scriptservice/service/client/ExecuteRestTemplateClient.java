package ru.igap.cophis.scriptservice.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExecuteRestTemplateClient {

    private final RestTemplate restTemplate;

    @Autowired
    public ExecuteRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getOrganization(String scriptName){
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        "http://execute-service:8003/execute/{scriptName}",
                        HttpMethod.GET,
                        null, String.class, scriptName);

        return restExchange.getBody();
    }
}
