package com.example.demo.repository;

import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MauSacDAO extends JpaRepository<MauSac,UUID> {
    MauSac findMauSacByMa(String ma);
}
