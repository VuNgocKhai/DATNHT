package com.example.demo.repository;

import com.example.demo.entity.DiaHinh;
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
public class DiaHinhRepo {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:2020/rest/diahinh";

    private String getUrl(String ma) {
        return url + "/" + ma;
    }

    public List<DiaHinh> getListDiaHinh() {
        ResponseEntity<List<DiaHinh>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<DiaHinh>>() {
                });

        return response.getBody();
    }
    public PageDTO<DiaHinh> getPageDiaHinh(Integer page) {
        ResponseEntity<PageDTO<DiaHinh>> response = restTemplate.exchange(
                getUrl("phantrang?page="+page),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDTO<DiaHinh>>() {}
        );
        return response.getBody();
    }

    public DiaHinh getDiaHinhByMa(String ma) {
        return restTemplate.getForObject(getUrl(ma), DiaHinh.class);
    }

    public String createDiaHinh(DiaHinh DiaHinh) {
        HttpEntity<DiaHinh> entity = new HttpEntity<>(DiaHinh);
        JsonNode resp = restTemplate.postForObject(url, entity, JsonNode.class);
        return resp.get("ten").asText();
    }

    public DiaHinh updateDiaHinh(String ma, DiaHinh DiaHinh) {
        HttpEntity<DiaHinh> entity = new HttpEntity<>(DiaHinh);
        restTemplate.put(getUrl(ma), DiaHinh);
        return DiaHinh;
    }

    public void delete(String ma) {
        restTemplate.delete(getUrl(ma));
    }
}
