package com.example.demo.repository;

import com.example.demo.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DiachiDao extends JpaRepository<DiaChi, UUID> {
    @Query("select p from DiaChi p where p.khachHang.ma = ?1")
    List<DiaChi> getAllByMaDiaChi(String ma);

    @Query("select p from DiaChi p where p.khachHang.id = ?1")
    DiaChi GetKhachhangByid(UUID id);


    @Query("select p from DiaChi p where p.khachHang.ma = ?1")
    List<DiaChi> getdiachibyma(String ma);

    @Query("select p from DiaChi p where p.madc = ?1")
    DiaChi getDiachiByma(String ma);
}
