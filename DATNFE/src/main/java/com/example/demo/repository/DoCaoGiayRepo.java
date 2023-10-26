package com.example.demo.repository;

import com.example.demo.entity.DoCaoGiay;
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
public class DoCaoGiayRepo {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:2020/rest/docaogiay";

    private String getUrl(String ma) {
        return url + "/" + ma;
    }

    public List<DoCaoGiay> getListDoCaoGiay() {
        ResponseEntity<List<DoCaoGiay>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<DoCaoGiay>>() {
                });

        return response.getBody();
    }
    public PageDTO<DoCaoGiay> getPageDoCaoGiay(Integer page) {
        ResponseEntity<PageDTO<DoCaoGiay>> response = restTemplate.exchange(
                getUrl("phantrang?page="+page),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDTO<DoCaoGiay>>() {}
        );
        return response.getBody();
    }

    public DoCaoGiay getDoCaoGiayByMa(String ma) {
        return restTemplate.getForObject(getUrl(ma), DoCaoGiay.class);
    }

    public String createDoCaoGiay(DoCaoGiay DoCaoGiay) {
        HttpEntity<DoCaoGiay> entity = new HttpEntity<>(DoCaoGiay);
        JsonNode resp = restTemplate.postForObject(url, entity, JsonNode.class);
        return resp.get("ten").asText();
    }

    public DoCaoGiay updateDoCaoGiay(String ma, DoCaoGiay DoCaoGiay) {
        HttpEntity<DoCaoGiay> entity = new HttpEntity<>(DoCaoGiay);
        restTemplate.put(getUrl(ma), DoCaoGiay);
        return DoCaoGiay;
    }

    public void delete(String ma) {
        restTemplate.delete(getUrl(ma));
    }
}
