package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HoaDonDAO extends JpaRepository<HoaDon, UUID> {

    @Query(value = "select hd from HoaDon hd where hd.nhanVien.ma=?1 and hd.trangthai=1")
    Page<HoaDon> findHdByMaNv(String maNv, Pageable pageable);

    @Query(value = "select hd from HoaDon hd where hd.khachHang.ma=?1 and (?2 IS NULL OR hd.trangthai=?2)")
    Page<HoaDon> findHdByMaKhAndTt(String maKh,Integer tt, Pageable pageable);
}
