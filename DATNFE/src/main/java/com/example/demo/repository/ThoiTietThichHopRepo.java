package com.example.demo.repository;

import com.example.demo.entity.ThoiTietThichHop;
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
public class ThoiTietThichHopRepo {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:2020/rest/thoitietthichhop";

    private String getUrl(String ma) {
        return url + "/" + ma;
    }

    public List<ThoiTietThichHop> getListThoiTietThichHop() {
        ResponseEntity<List<ThoiTietThichHop>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ThoiTietThichHop>>() {
                });

        return response.getBody();
    }
    public PageDTO<ThoiTietThichHop> getPageThoiTietThichHop(Integer page) {
        ResponseEntity<PageDTO<ThoiTietThichHop>> response = restTemplate.exchange(
                getUrl("phantrang?page="+page),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDTO<ThoiTietThichHop>>() {}
        );
        return response.getBody();
    }

    public ThoiTietThichHop getThoiTietThichHopByMa(String ma) {
        return restTemplate.getForObject(getUrl(ma), ThoiTietThichHop.class);
    }

    public String createThoiTietThichHop(ThoiTietThichHop ThoiTietThichHop) {
        HttpEntity<ThoiTietThichHop> entity = new HttpEntity<>(ThoiTietThichHop);
        JsonNode resp = restTemplate.postForObject(url, entity, JsonNode.class);
        return resp.get("ten").asText();
    }

    public ThoiTietThichHop updateThoiTietThichHop(String ma, ThoiTietThichHop ThoiTietThichHop) {
        HttpEntity<ThoiTietThichHop> entity = new HttpEntity<>(ThoiTietThichHop);
        restTemplate.put(getUrl(ma), ThoiTietThichHop);
        return ThoiTietThichHop;
    }

    public void delete(String ma) {
        restTemplate.delete(getUrl(ma));
    }
}
