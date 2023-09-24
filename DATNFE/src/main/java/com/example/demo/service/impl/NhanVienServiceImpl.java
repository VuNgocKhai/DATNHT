package com.example.demo.service.impl;

import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienDAO;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienDAO nhanVienDAO;

    @Override
    public Page<NhanVien> findNhanVien(Optional<String> ma, Optional<String> data, Optional<String> idCv, Integer number) {
        Pageable pageable = PageRequest.of(number, 5);
        String maF= ma.get().trim().isEmpty()?null:ma.get().trim();
        String dataF= data.get().trim().isEmpty()?null:data.get().trim();
        UUID idCvF= idCv.get().trim().isEmpty()?null:UUID.fromString(idCv.get());
        return nhanVienDAO.findNhanVien(
                maF,
                dataF,
                idCvF,
                pageable
        );
    }

    @Override
    public Page<NhanVien> getAllByTrangThai(Integer tt, Integer number) {
        Pageable pageable = PageRequest.of(number, 5);
        return nhanVienDAO.getAllByTrangThai(tt,pageable);
    }


}
