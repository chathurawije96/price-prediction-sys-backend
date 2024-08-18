package edu.icbt.pps.pricepredictionsystem.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ModelRestClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${model.url}")
    private String modelUrl;

    public double predictPrice(Map<String, Object> inputData) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(inputData, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(modelUrl, request, Map.class);

        Map<String,Object> responseBody = response.getBody();
        assert responseBody != null;
        return (Double) responseBody.get("predicted_price");
    }

}
