package com.example.demo.repository;

import com.example.demo.entity.Giay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface GiayDAO extends JpaRepository<Giay, UUID> {
    @Query("select p from Giay p where p.ma = ?1")
    Giay getGiayByMa(String ma);
@Query("select p from Giay p where p.ten like ?1 and (?2 is null or p.giaban>?2)")
    List<Giay> getSearch(String tensp, BigDecimal giabn);
}
