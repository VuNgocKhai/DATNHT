package com.example.demo.repository;

import com.example.demo.entity.CamGiac;
import com.example.demo.entity.PageDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class CamGiacRepo {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:2020/rest/camgiac";

    private String getUrl(String ma) {
        return url + "/" + ma;
    }

    public List<CamGiac> getListCamGiac() {
        ResponseEntity<List<CamGiac>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CamGiac>>() {
                });

        return response.getBody();
    }
    public PageDTO<CamGiac> getPageCamGiac(Integer page) {
        ResponseEntity<PageDTO<CamGiac>> response = restTemplate.exchange(
                getUrl("phantrang?page="+page),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDTO<CamGiac>>() {}
        );
        return response.getBody();
    }

    public CamGiac getCamGiacByMa(String ma) {
        return restTemplate.getForObject(getUrl(ma), CamGiac.class);
    }

    public String createCamGiac(CamGiac CamGiac) {
        HttpEntity<CamGiac> entity = new HttpEntity<>(CamGiac);
        JsonNode resp = restTemplate.postForObject(url, entity, JsonNode.class);
        return resp.get("ten").asText();
    }

    public CamGiac updateCamGiac(String ma, CamGiac CamGiac) {
        HttpEntity<CamGiac> entity = new HttpEntity<>(CamGiac);
        restTemplate.put(getUrl(ma), CamGiac);
        return CamGiac;
    }

    public void delete(String ma) {
        restTemplate.delete(getUrl(ma));
    }
}
