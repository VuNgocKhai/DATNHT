package com.example.demo.repository;

import com.example.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface KhachHangDao extends JpaRepository<KhachHang, UUID> {
    @Query("select p from KhachHang p where p.email=?1")
    KhachHang getKhByEmail(String email);
}
