package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
=======
>>>>>>> origin/giamgiasanpham

import java.util.List;
import java.util.UUID;

public interface HoaDonDAO extends JpaRepository<HoaDon, UUID> {

    // Query tìm hóa đơn theo mã
    @Query("select hd from HoaDon hd where hd.ma=?1")
    HoaDon findHoaDonByMa(String ma);

    // Query tìm hóa đơn với điều kiện hóa đơn chưa được áp mã và trạng thái phải là chưa thanh toán
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangthai = 0 AND hd.id NOT IN (SELECT ct.hd.id FROM GiamGiaChiTietHoaDon ct WHERE ct.hd.trangthai = 0)")
    List<HoaDon> findHoaDonChuaApDungChuongTrinhGiamGia();

<<<<<<< HEAD
    // Query tìm hóa đơn với điều kiện hóa đơn chưa được áp mã ,trạng thái phải là chưa thanh toán và khách hàng không được null
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangthai = 0 " +
            "AND hd.khachHang IS NOT NULL " +
            "AND hd.id NOT IN (SELECT ct.hd.id FROM GiamGiaChiTietHoaDon ct WHERE ct.hd.trangthai = 0)")
    Page<HoaDon> findHoaDonChuaApDungChuongTrinhGiamGiaPage(Pageable pageable);


    // Query getall hóa đơn chưa thanh toán
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangthai = 0")
    Page<HoaDon> findHoaDonChuaThanhToan(Pageable pageable);

    //Query tìm kiếm hóa đơn theo mã hóa đơn hoặc tên khách hàng
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangthai = 0 " +
            "AND (hd.ma LIKE %:keyword% OR hd.khachHang.hoten LIKE %:keyword%)" +
            "and hd.trangthai = 0")
    Page<HoaDon> searchHoaDonByKeyword(@Param("keyword") String keyword, Pageable pageable);


=======
    // Query tìm hóa đơn với điều kiện hóa đơn chưa được áp mã và trạng thái phải là chưa thanh toán
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangthai = 0 AND hd.id NOT IN (SELECT ct.hd.id FROM GiamGiaChiTietHoaDon ct WHERE ct.hd.trangthai = 0)")
    Page<HoaDon> findHoaDonChuaApDungChuongTrinhGiamGiaPage(Pageable pageable);
>>>>>>> origin/giamgiasanpham
}
