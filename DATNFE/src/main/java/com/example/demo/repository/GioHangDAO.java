package com.example.demo.repository;

import com.example.demo.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GioHangDAO extends JpaRepository<GioHang, UUID> {
}
