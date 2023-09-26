package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HoaDonDAO extends JpaRepository<HoaDon, UUID> {

    // Query tìm hóa đơn theo mã
    @Query("select hd from HoaDon hd where hd.ma=?1")
    HoaDon findHoaDonByMa(String ma);

    // Query tìm hóa đơn với điều kiện hóa đơn chưa được áp mã và trạng thái phải là chưa thanh toán
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangthai = 0 AND hd.id NOT IN (SELECT ct.hd.id FROM GiamGiaChiTietHoaDon ct WHERE ct.hd.trangthai = 0)")
    List<HoaDon> findHoaDonChuaApDungChuongTrinhGiamGia();


}
