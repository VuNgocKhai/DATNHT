package com.example.demo.service;

import com.example.demo.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface NhanVienService {

    Page<NhanVien> findNhanVien(Optional<String> ma, Optional<String> data, Optional<String> idCv, Integer number);

    Page<NhanVien> getAllByTrangThai(Integer tt, Integer number);


}
