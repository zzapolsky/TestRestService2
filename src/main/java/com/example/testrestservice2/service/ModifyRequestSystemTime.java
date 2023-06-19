package com.example.testrestservice2.service;

import com.example.testrestservice2.model.Request;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service

public class ModifyRequestSystemTime implements ModifyRequestService {

    @Override
    public void modifyRq(Request request) {


        request.setSystemTime("test");      // Установить текущее время, преобразовать в строку


        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8082/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}
