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

    @Query(value = "select hd from HoaDon hd where hd.nhanVien.ma=?1 and hd.trangThai=1")
    Page<HoaDon> findHdByMaNv(String maNv, Pageable pageable);
}
