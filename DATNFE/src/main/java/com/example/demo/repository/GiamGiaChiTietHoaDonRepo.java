package com.example.demo.repository;

import com.example.demo.entity.GiamGiaChiTietHoaDon;
import com.example.demo.entity.GiamGiaHoaDon;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.PageDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class GiamGiaChiTietHoaDonRepo {

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:2020/rest/giamgiachitiethoadon";

    String url1 = "http://localhost:2020/rest/giamgiahoadon";

    private String getUrldelete(UUID hdid, UUID gghdid) {
        return url + "/delete" + "/" + hdid + "/" + gghdid;
    }

    // url theo mã
    private String getUrl1(String ma) {
        return url + "/" + ma;
    }


    // get all giảm giá CT hóa đơn
    public List<GiamGiaChiTietHoaDon> getAllGGCTHD() {
        ResponseEntity<List<GiamGiaChiTietHoaDon>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<GiamGiaChiTietHoaDon>>() {
                });

        return response.getBody();
    }

    // Lấy danh sách hóa đơn đã áp dụng giảm giá từ chương trình giảm giá cụ thể
    public List<HoaDon> getHoaDonByChuongTrinhGiamGia(UUID chuongTrinhGiamGiaId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UUID> requestEntity = new HttpEntity<>(chuongTrinhGiamGiaId, headers);

        ResponseEntity<List<HoaDon>> response = restTemplate.exchange(
                url + "/hoandonbygiamgia/{chuongTrinhGiamGiaId}",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<HoaDon>>() {
                },
                chuongTrinhGiamGiaId);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            System.err.println("Lỗi khi lấy danh sách hóa đơn đã áp dụng giảm giá.");
            return Collections.emptyList();
        }
    }

    // tạo mới GGCTHD
    public String createGGCTHD2(GiamGiaChiTietHoaDon giamGiaChiTietHoaDon) {
        HttpEntity<GiamGiaChiTietHoaDon> entity = new HttpEntity<>(giamGiaChiTietHoaDon);
        JsonNode resp = restTemplate.postForObject(url + "/createGGCTHD", entity, JsonNode.class);
        return resp.get("trangthai").asText();
    }

    //xóa GGCTHD
    public void deleteGGCTHD(UUID hdid, UUID gghdid) {
        restTemplate.delete(getUrldelete(hdid, gghdid));
    }
}
