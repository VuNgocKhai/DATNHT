package com.example.demo.repository;

import com.example.demo.entity.CamGiac;
import com.example.demo.entity.Giay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface AnhGiayDAO extends JpaRepository<Giay, UUID> {
    @Query("select p from Anh p where p.giay.ma=?1")
    List<Giay> getGiayByMaGiay(String ma);
}
