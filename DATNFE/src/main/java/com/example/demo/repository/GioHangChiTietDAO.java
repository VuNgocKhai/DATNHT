package com.example.demo.repository;

import com.example.demo.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GioHangChiTietDAO extends JpaRepository<GioHangChiTiet, UUID> {
}
