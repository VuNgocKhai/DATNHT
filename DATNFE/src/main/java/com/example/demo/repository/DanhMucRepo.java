package com.example.demo.repository;

import com.example.demo.entity.DanhMuc;
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
public class DanhMucRepo {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:2020/rest/danhmuc";

    private String getUrl(String ma) {
        return url + "/" + ma;
    }

    public List<DanhMuc> getListDanhMuc() {
        ResponseEntity<List<DanhMuc>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<DanhMuc>>() {
                });

        return response.getBody();
    }
    public PageDTO<DanhMuc> getPageDanhMuc(Integer page) {
        ResponseEntity<PageDTO<DanhMuc>> response = restTemplate.exchange(
                getUrl("phantrang?page="+page),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageDTO<DanhMuc>>() {}
        );
        return response.getBody();
    }

    public DanhMuc getDanhMucByMa(String ma) {
        return restTemplate.getForObject(getUrl(ma), DanhMuc.class);
    }

    public String createDanhMuc(DanhMuc danhMuc) {
        HttpEntity<DanhMuc> entity = new HttpEntity<>(danhMuc);
        JsonNode resp = restTemplate.postForObject(url, entity, JsonNode.class);
        return resp.get("ten").asText();
    }

    public DanhMuc updateDanhMuc(String ma, DanhMuc danhMuc) {
        HttpEntity<DanhMuc> entity = new HttpEntity<>(danhMuc);
        restTemplate.put(getUrl(ma), danhMuc);
        return danhMuc;
    }

    public void delete(String ma) {
        restTemplate.delete(getUrl(ma));
    }
}
