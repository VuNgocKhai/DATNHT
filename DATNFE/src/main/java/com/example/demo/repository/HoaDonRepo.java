package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.PageDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class HoaDonRepo {

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:2020/rest/hoadon";

    //url theo id
    private String getUrl1(UUID id) {
        return url + "/" + id;
    }

    // url theo mã
    private String getUrl(String ma) {
        return url + "/" + ma;
    }

    // get all  hóa đơn chưa giảm giá theo page
    public PageDTO<HoaDon> getAllHDchuaGGPage(Integer page) {
        ResponseEntity<PageDTO<HoaDon>> response =
                restTemplate.exchange(url + "/phantrang?page1=" + page, HttpMethod.GET, null, new ParameterizedTypeReference<PageDTO<HoaDon>>() {
                });
        return response.getBody();
    }

    // findGGHD by mã
    public HoaDon getHoaDonByMa(String ma) {
        return restTemplate.getForObject(getUrl(ma), HoaDon.class);
    }
}
