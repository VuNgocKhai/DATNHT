package com.example.demo.repository;

import com.example.demo.entity.ChatLieu;
import com.example.demo.entity.GioiTinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatLieuDAO extends JpaRepository<ChatLieu,UUID> {
    ChatLieu findChatLieuByMa(String ma);
}
