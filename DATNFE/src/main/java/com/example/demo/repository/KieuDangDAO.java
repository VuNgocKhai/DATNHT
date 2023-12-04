package com.example.demo.repository;

import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.KieuDang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KieuDangDAO extends JpaRepository<KieuDang,UUID> {
    KieuDang findKieuDangByTen(String ten);
}
