package com.example.demo.repository;

import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HoaDonDAO extends JpaRepository<HoaDon, UUID> {

    @Query(value = "select hd from HoaDon hd where hd.nhanVien.ma=?1 and hd.trangthai=1")
    Page<HoaDon> findHdByMaNv(String maNv, Pageable pageable);

    @Query(value = "select hd from HoaDon hd where hd.khachHang.ma=?1 and (?2 IS NULL OR hd.trangthai=?2)")
    Page<HoaDon> findHdByMaKhAndTt(String maKh, Integer tt, Pageable pageable);

    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangthai = 0 " +
            "AND (hd.ma LIKE %:keyword% OR hd.khachHang.hoten LIKE %:keyword%)" +
            "and hd.trangthai = 0")
    Page<HoaDon> searchHoaDonByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT hd FROM HoaDon hd WHERE hd.ma LIKE %:keyword% AND hd.trangthai = :trangThai")
    Page<HoaDon> searchHoaDon(@Param("keyword") String keyword, @Param("trangThai") Integer trangThai, Pageable pageable);

    @Query("SELECT MAX(hd.ma) FROM HoaDon hd")
    String findHighestMaHoaDon();

    void deleteByMa(String ma);

    @Query("SELECT MAX(CAST(SUBSTRING(hd.ma, 3, LENGTH(hd.ma) - 2) AS int)) FROM HoaDon hd")
    Integer findMaxMaHoaDonNumber();

    default String generateNextMaHoaDon() {
        Integer maxMaNumber = findMaxMaHoaDonNumber();
        int nextNumber;

        if (maxMaNumber != null) {
            nextNumber = maxMaNumber + 1;
        } else {
            nextNumber = 1;
        }

        return "HD" + nextNumber;
    }


    @Query("SELECT SUM(tong_tien) " +
            "FROM HoaDon " +
            "WHERE trangthai = 4 AND ngay_thanh_toan = :ngayHienTai")
    BigDecimal tongSoTienHomNay(@Param("ngayHienTai") LocalDate ngayHienTai);

    @Query("SELECT COUNT(*) AS TongSoLuongTrangThai1\n" +
            "FROM HoaDon\n" +
            "WHERE trangthai = 1\n")
    Integer tongsohoadondangchoxanhan();
}
