package com.example.demo.repository;

import com.example.demo.entity.DanhMuc;
import com.example.demo.entity.ThoiTietThichHop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface ThoiTietThichHopDAO extends JpaRepository<ThoiTietThichHop, UUID> {
    @Query("select p from ThoiTietThichHop p where p.ma=?1")
    ThoiTietThichHop findThoiTietThichHopByMa(String ma);

    @Modifying
    @Transactional
    @Query("delete from ThoiTietThichHop where ma=?1")
    void deleteThoiTietThichHopByMa(String ma);
}
